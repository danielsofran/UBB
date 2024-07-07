import communication.FinalRanking;
import communication.Request;
import communication.RequestType;
import dataTypes.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Server {
    private int pR;
    private int pW;
    private int t;
    private int x;
    private List<Integer> banList = Collections.synchronizedList(new ArrayList<>());
    private Map<Integer, Socket> clients = Collections.synchronizedMap(new HashMap<>());
    private Map<Integer, ObjectOutputStream> outputStreamList = Collections.synchronizedMap(new HashMap<>());
    private Map<Integer, ObjectInputStream> inputStreamList = Collections.synchronizedMap(new HashMap<>());
    private BlockingQueue<ContestantData> contestantQueue = new LinkedBlockingQueue<>();
    private List<ContestantData> contestantDataList = Collections.synchronizedList(new ArrayList<>(){
        @Override
        public boolean add(ContestantData contestantData) {
            if (this.contains(contestantData)) {
                ContestantData contestantData1 = this.stream().filter(x -> x.getId() == contestantData.getId()).findFirst().orElse(null);
                contestantData1.setScore(contestantData1.getScore() + contestantData.getScore());
                this.sort(ContestantData::compareTo);
            } else {
                int index = Collections.binarySearch(this, contestantData);
                if (index < 0) {
                    index = -index - 1;  // Adjust the index for insertion
                }
                super.add(index, contestantData);
            }
            return true;
        }
    });
    private AtomicBoolean listChanged = new AtomicBoolean(false);
    private boolean[] finishedReading = new boolean[5];
    private boolean[] finishedProccesing = new boolean[8];
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    ExecutorService startServices = Executors.newFixedThreadPool(3);

    public Server(int pR, int pW, int t, int x) {
        this.pR = pR;
        this.pW = pW;
        this.t = t;
        this.x = x;
    }

    public void run() {
        try {
            startServices.submit(this::startReaderThread);
            startServices.submit(this::startWorkingThreads);
            startServices.submit(this::startClients);
            boolean allFinished = false;
            while (!allFinished) {
                allFinished = true;
                for (int i = 0; i < 5; i++) {
                    if (!finishedReading[i]) {
                        allFinished = false;
                        break;
                    }
                }
                for (int i = 0; i < pW; i++) {
                    if (!finishedProccesing[i]) {
                        allFinished = false;
                        break;
                    }
                }
            }
            List<CountryData> countryData = getCountryRanking();
            List<ContestantData> contestantData = new ArrayList<>(contestantDataList);
            countryData.sort(CountryData::compareTo);
            FinalRanking finalRanking = new FinalRanking(contestantData, countryData);
            try (FileWriter contenstantWriter = new FileWriter("RezultateConcurenti.txt");
                 FileWriter countryWriter = new FileWriter("RezultateTari.txt")) {
                contenstantWriter.write(finalRanking.getContestantRanking());
                countryWriter.write(finalRanking.getCountryRanking());
            }
            outputStreamList.forEach((integer, objectOutputStream) -> {
                try {
                    objectOutputStream.writeObject(finalRanking);
                    objectOutputStream.close();
                    inputStreamList.get(integer).close();
                    clients.get(integer).close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            startServices.shutdown();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    private void startClients() {
        Thread clientThread[] = new Thread[5];
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            clientThread[i] = new Thread(() -> {
                try {
                    List<ContestantData> contestantData = new ArrayList<>();
                    for (int j = 1; j <= 10; j++) {
                        String fileName = "input/RezultateC" + (finalI + 1) + "_P" + j + ".txt";
                        BufferedReader inputReader = new BufferedReader(new FileReader(fileName));
                        String line;
                        while ((line = inputReader.readLine()) != null) {
                            String[] tokens = line.split(" ");
                            int id = Integer.parseInt(tokens[0]);
                            int score = Integer.parseInt(tokens[1]);
                            contestantData.add(new ContestantData(id, finalI + 1, score));
                        }
                    }
                    String filePath = "output/" + (finalI + 1) + "/";
                    try (Socket socket = new Socket("localhost", 5000 + finalI);
                         ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                         FileWriter contenstantWriter = new FileWriter(filePath + "RezultateConcurenti.txt");
                         FileWriter countryWriter = new FileWriter(filePath + "RezultateTari.txt")) {
                        while (!contestantData.isEmpty()) {
                            List<ContestantData> toBeSent = new ArrayList<>(contestantData.subList(0, Math.min(20, contestantData.size())));
                            contestantData.subList(0, Math.min(20, contestantData.size())).clear();
                            sleep(x);
                            objectOutputStream.writeObject(new Request(RequestType.DATA, toBeSent));
                        }
                        System.out.println("Finished sending data" + finalI);
                        objectOutputStream.writeObject(new Request(RequestType.RANKING, finalI));
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        List<CountryData> currentRanking = (List<CountryData>) objectInputStream.readObject();
                        objectOutputStream.writeObject(new Request(RequestType.FINAL_RANKING, finalI));

                        FinalRanking finalRanking = (FinalRanking) objectInputStream.readObject();
                        contenstantWriter.write(finalRanking.getContestantRanking());
                        countryWriter.write(finalRanking.getCountryRanking());
                    } catch (InterruptedException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            clientThread[i].start();
        }
        for (int i = 0; i < 5; i++) {
            try {
                clientThread[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<CountryData> getCountryRanking() {
        List<CountryData> countryData = new ArrayList<>();
        List<ContestantData> contestantData = new ArrayList<>(contestantDataList);
        for (ContestantData contestantDatum : contestantData) {
            if (countryData.stream().noneMatch(countryDatum -> countryDatum.getId() == contestantDatum.getCountryId())) {
                countryData.add(new CountryData(contestantDatum.getCountryId(), contestantDatum.getScore()));
            } else {
                countryData.stream().filter(countryDatum -> countryDatum.getId() == contestantDatum.getCountryId()).findFirst().get().addScore(contestantDatum.getScore());
            }
        }
        countryData.sort(CountryData::compareTo);

        return countryData;
    }

    private void startReaderThread() {
        List<ServerSocket> serverSockets = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            try {
                ServerSocket serverSocket = new ServerSocket(5000 + j);
                serverSockets.add(serverSocket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (int j = 0; j < 5; j++) {
            try {
                Socket socket = serverSockets.get(j).accept();
                clients.put(j, socket);
                serverSockets.get(j).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Thread[] readerThreads = new Thread[pR];
        AtomicInteger clientsRead = new AtomicInteger(0);
        for (int i = 0; i < pR; i++) {
            readerThreads[i] = new Thread(() -> {
                boolean allRead = false;
                while (!allRead && clientsRead.get() < 5) {
                    //modifica while-ul asta
                    int currValue = clientsRead.getAndIncrement();
                    if (currValue >= 5) {
                        break;
                    }
                    System.out.println(currValue);
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clients.get(currValue).getOutputStream());
                        ObjectInputStream objectInputStream = new ObjectInputStream(clients.get(currValue).getInputStream());
                        Request request;
                        outputStreamList.put(currValue, objectOutputStream);
                        inputStreamList.put(currValue, objectInputStream);
                        while ((request = (Request) objectInputStream.readObject()) != null) {
                            if (request.getType() == RequestType.DATA) {
                                List<ContestantData> contestantData = (List<ContestantData>) request.getData();
                                for (ContestantData contestantDatum : contestantData) {
                                    contestantQueue.put(contestantDatum);
                                }
                            } else if (request.getType() == RequestType.RANKING) {
                                while (listChanged.get()) {
                                    listChanged.set(false);
                                    sleep(t);
                                }
                                Future<List<CountryData>> countryRanking = executorService.submit(this::getCountryRanking);
                                objectOutputStream.writeObject(countryRanking.get());
                            } else {
                                int clientCountry = (int) request.getData();
                                finishedReading[clientCountry] = true;
                                break;
                            }
                        }
                    } catch (IOException | ClassNotFoundException | InterruptedException | ExecutionException | IndexOutOfBoundsException e) {
                        throw new RuntimeException(e);
                    }
                    for (int k = 0; k < 5; k++) {
                        if (!finishedReading[k]) {
                            allRead = false;
                            break;
                        }
                        allRead = true;
                    }
                }
            });
            readerThreads[i].start();
        }
        for (int i = 0; i < pR; i++) {
            try {
                readerThreads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdown();
    }


    private void startWorkingThreads() {
        Thread[] executingThreads = new Thread[pW];
        for (int i = 0; i < pW; i++) {
            int finalI = i;
            executingThreads[i] = new Thread(() -> {
                boolean allFinished = false;
                try {
                    while (!allFinished || !contestantQueue.isEmpty()) {
                        allFinished = true;
                        for (int j = 0; j < 5; j++) {
                            if (!finishedReading[j]) {
                                allFinished = false;
                                break;
                            }
                        }
                        if (!contestantQueue.isEmpty()) {
                            ContestantData contestantData = contestantQueue.take();
                            if (banList.contains(contestantData.getId())) {
                                continue;
                            }
                            if (contestantData.getScore() < 0) {
                                banList.add(contestantData.getId());
                                continue;
                            }
                            contestantDataList.add(contestantData);
                            listChanged.set(true);
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                finishedProccesing[finalI] = true;
            });
            executingThreads[i].start();
            try {
                executingThreads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
