import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class OlimpTest {

    class Results{
        private int id_conc;
        private int id_prob;
        private int punctaj;
        private int t_inc;

        public Results(int id_conc, int id_prob, int punctaj, int t_inc) {
            this.id_conc = id_conc;
            this.id_prob = id_prob;
            this.punctaj = punctaj;
            this.t_inc = t_inc;
        }

        public int getId_conc() {
            return id_conc;
        }

        public void setId_conc(int id_conc) {
            this.id_conc = id_conc;
        }

        public int getId_prob() {
            return id_prob;
        }

        public void setId_prob(int id_prob) {
            this.id_prob = id_prob;
        }

        public int getPunctaj() {
            return punctaj;
        }

        public void setPunctaj(int punctaj) {
            this.punctaj = punctaj;
        }

        public int getT_inc() {
            return t_inc;
        }

        public void setT_inc(int t_inc) {
            this.t_inc = t_inc;
        }
    }

    class PrelimResult{
        private int id_conc;
        private int punctaj;
        private int t_inc;

        public PrelimResult(int id_conc, int punctaj, int t_inc) {
            this.id_conc = id_conc;
            this.punctaj = punctaj;
            this.t_inc = t_inc;
        }
        public PrelimResult(Results res) {
            this.id_conc = res.getId_conc();
            this.punctaj = res.getPunctaj();
            this.t_inc = res.getT_inc();
        }

        public int getId_conc() {
            return id_conc;
        }

        public void setId_conc(int id_conc) {
            this.id_conc = id_conc;
        }

        public int getPunctaj() {
            return punctaj;
        }

        public void setPunctaj(int punctaj) {
            this.punctaj = punctaj;
        }

        public int getT_inc() {
            return t_inc;
        }

        public void setT_inc(int t_inc) {
            this.t_inc = t_inc;
        }
    }
    class Pool{
        protected int nrElems;
        protected List<Results> queue;

        private int nrRead;

        private int nrCons;
        private int PMAX;

        public Pool(int max, int nrRead, int nrCons) {
            this.queue = new ArrayList<>();
            this.nrElems = 0;
            this.PMAX = max;
            this.nrRead = nrRead;
            this.nrCons = nrCons;
        }

        synchronized public void push(List<Results> elems){
            try {
                while (this.nrElems + elems.size() > PMAX && this.nrCons > 0)
                    this.wait();
                if(this.nrElems + elems.size() <= PMAX) {
                    this.queue.addAll(elems);
                    this.nrElems += elems.size();
                }
                this.notifyAll();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        synchronized public Results extract(int id_prob){
            Results obj = null;
            try {
                while ((this.nrElems - 1 < 0 || this.queue.get(0).getId_prob() != id_prob) && this.nrRead > 0)
                    this.wait();
                if(this.nrElems - 1 >= 0 && this.queue.get(0).getId_prob() == id_prob) {
                    obj = this.queue.get(0);
                    this.queue.remove(0);
                    this.nrElems--;
                }
                this.notifyAll();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return obj;
        }
        public int size(){return this.nrElems;}

        public synchronized void exitRead(){
            if(this.nrRead > 0)this.nrRead--;
            this.notifyAll();
        }

        public synchronized void exitCons(){
            if(this.nrCons > 0)this.nrCons--;
            this.notifyAll();
        }

        public boolean isEmpty(){
            return this.nrElems == 0;
        }
    }
    class GlobalQueue{
        protected int nrElems;
        protected List<PrelimResult> queue;

        private int deadline;

        public GlobalQueue(int deadline) {
            this.queue = new ArrayList<>();
            this.nrElems = 0;
            this.deadline = deadline;
        }



        synchronized public void push(PrelimResult elem){
            boolean found = false;
            for(var el: this.queue){
                if(el.getId_conc() == elem.getId_conc()){
                    el.setPunctaj(el.getPunctaj()+ elem.getPunctaj());
                    found = true;
                    break;
                }
            }
            if(!found) {
                this.queue.add(elem);
                this.queue.sort((a, b) -> {
                    var res = a.getPunctaj() - b.getPunctaj();
                    if (res == 0) res = (deadline - a.getT_inc()) - (deadline - b.getT_inc());
                    return res;
                });
                this.nrElems++;
            }

        }

//        synchronized public PrelimResult extract(){
//            if(this.nrElems == 0) return null;
//            var obj = this.queue.get(0);
//            this.queue.remove(0);
//            this.nrElems--;
//            return obj;
//        }
        public int size(){return this.nrElems;}

        public boolean isEmpty(){
            return this.nrElems == 0;
        }
    }


    class ProblemQueue {
        protected int nrElems;
        protected List<PrelimResult> queue;

        private int deadline;
        public ProblemQueue(int deadline) {
            this.queue = new ArrayList<>();
            this.nrElems = 0;
            this.deadline = deadline;
        }
        synchronized public void push(PrelimResult elem){
            this.queue.add(elem);
            this.queue.sort((a, b) -> {
                var res = a.getPunctaj() - b.getPunctaj();
                if (res == 0) res = (deadline - a.getT_inc()) - (deadline - b.getT_inc());
                return res;
            });
            this.nrElems++;
        }

        synchronized public void save(String filename){
            try {
                FileWriter fileWriter = new FileWriter(filename);
                for(var el: this.queue){
                    fileWriter.write(el.getId_conc()+","+el.getPunctaj()+","+ el.getT_inc());
                }
                fileWriter.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

    }

//    class EvidenceStatus{
//        private int code;
//        private List<Integer> history;
//
//        public EvidenceStatus() {
//            code = 0;
//            this.history = new ArrayList<>();
//            this.history.add(0);
//        }
//
//        public int getCode() {
//            return code;
//        }
//
//        public void setCode(int code) {
//            this.code = code;
//        }
//
//        public List<Integer> getHistory() {
//            return history;
//        }
//
//        public void setHistory(List<Integer> history) {
//            this.history = history;
//        }
//    }
//
//    class Producer extends Thread{
//        private GlobalQueue queue;
//
//        private ServiceQueue queue1;
//        private ServiceQueue queue3;
//        private ServiceQueue queue4;
//        public Producer(GlobalQueue queue,ServiceQueue queue1,ServiceQueue queue3,ServiceQueue queue4){
//            this.queue = queue;
//            this.queue1 = queue1;
//            this.queue3 = queue3;
//            this.queue4 = queue4;
//
//        }
//
//        @Override
//        public void run() {
//            System.out.println("Producer "+ Thread.currentThread().getId() +" started");
//            while(true){
//                try {
//                    Ticket tic = null;
//                    synchronized (queue) {
//                        if (queue.isEmpty())
//                            queue.wait();
//                        ///break condition here
//                        if(!queue.isEmpty())
//                            tic = queue.extract();
//                        System.out.println("Producer "+ Thread.currentThread().getId() +" extracted from queue");
//                    }
//                    if(tic!= null){
//                        if(tic.getId() == 1){
//                            synchronized (queue1){
//                                queue1.push(tic);
//                                queue1.notify();
//                            }
//                        }
//                        else if(tic.getId() == 3){
//                            synchronized (queue3){
//                                queue3.push(tic);
//                                queue3.notify();
//                            }
//                        }
//                        else if(tic.getId() == 4){
//                            synchronized (queue4){
//                                queue4.push(tic);
//                                queue4.notify();
//                            }
//                        }
//                        System.out.println("Producer "+ Thread.currentThread().getId() +" added to a queue " + tic.getId());
//                    }
//                    else{
//                        synchronized (nrReader){
//                            if(nrReader == 0){
//                                synchronized (queue){
//                                    queue.notifyAll();
//                                }
//                                break;
//                            }
//                        }
//                    }
//                }catch(Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//            synchronized (nrProducer){
//                nrProducer--;
//                synchronized (queue1) {
//                    queue1.notifyAll();
//                }
//                synchronized (queue3) {
//                    queue3.notifyAll();
//                }
//                synchronized (queue4) {
//                    queue4.notifyAll();
//                }
//            }
//            System.out.println("Producer "+ Thread.currentThread().getId() +" ended");
//        }
//    }

    class Consumer extends Thread{
        private int serviceId;
        private Pool pool;

        private ProblemQueue probQueue;
        private GlobalQueue globalQueue;
//        private Map<Integer, EvidenceStatus> list;

        private long timeMs;

        public Consumer(int serviceId,ProblemQueue queue,Pool pool, GlobalQueue additionalQueue){
            this.probQueue = queue;
            this.serviceId = serviceId;
            this.globalQueue = additionalQueue;
            this.pool = pool;
        }

        @Override
        public void run() {
            System.out.println("Consumer "+ Thread.currentThread().getId() +" started " + serviceId);
            while(true){
                try {
                    Results res = null;
                    res = pool.extract(serviceId);
                    System.out.println("Consumer "+ Thread.currentThread().getId() +" extracted ");
                    if(res != null){
                        PrelimResult prem = new PrelimResult(res);
                        probQueue.push(prem);
                        System.out.println("Consumer "+ Thread.currentThread().getId() +" added to prob queue");
                        globalQueue.push(prem);
                        System.out.println("Consumer "+ Thread.currentThread().getId() +" added to global queue");
//                        boolean initialized = false;
//                        synchronized (list){
//                            if(!list.containsKey(tic.getId())){
//                                list.put(tic.getId(), new EvidenceStatus());
//                                initialized = true;
//                            }
//                        }
//                        if(!initialized){
//                            EvidenceStatus ev = null;
//                            boolean inService = false;
//                            synchronized (ev =list.get(tic.getId())){
//                                if(ev.getCode() == 0){
//                                    ev.setCode(serviceId);
//                                    inService = true;
//                                }
//                            }
//                            if(inService) {
//                                try {
//                                    Thread.sleep(timeMs);
//                                } catch (InterruptedException e) {
//                                    throw new RuntimeException(e);
//                                }
//                                synchronized (ev = list.get(tic.getId())) {
//                                    ev.setCode(0);
//                                    ev.getHistory().add(serviceId);
//                                }
//                                if(serviceId == 1){
//                                    Random rand = new Random();
//                                    int needRadio = Math.abs(rand.nextInt() % 10) + 1;
//                                    if(needRadio == 1){
//                                        synchronized (additionalQueue){
//                                            additionalQueue.push(new Ticket(tic.getId(), 2));
//                                        }
//                                    }
//                                }
//                            }
//                        }
                    }
                    else{
                        synchronized (nrReader){
                            if(nrReader == 0){
                                synchronized (pool){
                                    pool.notifyAll();
                                }
                                break;
                            }
                        }
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }

            pool.exitCons();
            //probQueue.save("out"+serviceId+".txt");
            synchronized (nrConsumer){
                nrConsumer--;
//                synchronized (pool){
//                    pool.notifyAll();
//                }
            }
            System.out.println("Consumer "+ Thread.currentThread().getId() +" ended "+ serviceId);
        }
    }

    class Reader extends Thread{
        //private long timeMs;

        private List<Scanner> filesHandlers;

        private Pool pool;

        private int max;

        public Reader(List<Scanner> filesHandlers, Pool pool){
            this.filesHandlers = filesHandlers;
            this.pool = pool;
        }

        @Override
        public void run() {
            System.out.println("Reader "+ Thread.currentThread().getId() +" started");
            try {
                boolean done = false;
                while(!done){
                    done = true;
                    for(var handler: filesHandlers){
                        synchronized (handler) {
                            if (handler.hasNextLine()) {
                                List<Results> listElems = new ArrayList<>();
                                for(int i =0;i< max;i++) {
                                    String line = handler.nextLine();
                                    System.out.println("Reader "+ Thread.currentThread().getId() +" read a line");
                                    if (!line.isEmpty()) {
                                        String[] elems = line.split(",");
                                        Results tic = new Results(Integer.parseInt(elems[0]), Integer.parseInt(elems[1]), Integer.parseInt(elems[2]), Integer.parseInt(elems[3]));
                                        listElems.add(tic);
                                        done = false;
                                    }
                                }
                                pool.push(listElems);
                                System.out.println("Reader "+ Thread.currentThread().getId() +" added to pool");
                            }

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            pool.exitRead();
            synchronized (nrReader){
                nrReader--;
//                synchronized (pool) {
//                    pool.notifyAll();
//                }
            }
            System.out.println("Reader "+ Thread.currentThread().getId() +" ended");
        }
    }

    private Integer nrReader;
    private Integer nrConsumer;
    private Integer currentNumberThreads;


    private void initEntities(int numCons, int nrRea, List<String> filenames, List<Integer> probTimes){
        this.nrReader = nrRea;
        this.nrConsumer = numCons;
        this.currentNumberThreads = nrReader + nrConsumer;
        int bigDead = 0;
        for(var el:probTimes)
            bigDead+= el;
        Pool pool = new Pool(10,nrRea,numCons);
        GlobalQueue gqueue = new GlobalQueue(bigDead);
        List<ProblemQueue> listProbQueue = new ArrayList<>();
        int currentDeadline = 0;
        for(int i=0;i<nrConsumer;i++) {
            currentDeadline += probTimes.get(i);
            listProbQueue.add(new ProblemQueue(currentDeadline));
        }
        var filesHandlers = new ArrayList<Scanner>();
        for (var file : filenames){
            try {
                filesHandlers.add(new Scanner(new File(file)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Thread[] ths = new Thread[currentNumberThreads];
        var startTime = System.nanoTime();
        for(int i=0;i<nrReader;i++) {
            ths[i] = new Reader(filesHandlers, pool);
            ths[i].start();
        }
        for(int i=0;i<numCons;i++) {
            ths[nrReader + i] = new Consumer(i+1,listProbQueue.get(i), pool, gqueue);
            ths[nrReader + i].start();
        }

        for(int i=0;i<currentNumberThreads;i++){
            try {
                ths[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        var endTime = System.nanoTime();
        System.out.println((double)(endTime - startTime)/1E6);
    }

    private void generateData(String filename, int numEnt, List<Integer> listTimes){
        Random rand = new Random();
        int delta = 5;
        try{
            FileWriter fileWriter = new FileWriter(filename);
            for(int i =0;i< numEnt;i++){
                int id_c = Math.abs(rand.nextInt(1000))+1;
                int id_p = Math.abs(rand.nextInt(5)) + 1;
                int punct = Math.abs(rand.nextInt(90)) + 10;
                int pred = id_p > 1? id_p -1 :0;
                int tc = Math.abs(rand.nextInt(listTimes.get(id_p-1) - listTimes.get(pred)+ delta)) + listTimes.get(pred);
                fileWriter.write(id_c + "," + id_p+ "," + punct + "," + tc + "\n");
            }
            fileWriter.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Num Prods: ");
//        int numProd = scan.nextInt();
//        System.out.println("Num Cons: ");
//        int numCons = scan.nextInt();
//        System.out.println("Cap Banda: ");
//        int capBanda = scan.nextInt();
        var olimp = new OlimpTest();
        int j =10;
        List<String> filenames= new ArrayList<>();
        List<Integer> listTimes = Arrays.asList(30,45,60,20,70);
        List<Integer> deadlines = Arrays.asList(30,75,135,155,225);
        for(int i=0;i<j;i++){
            String filename = "in"+i+".txt";
            filenames.add(filename);
            olimp.generateData(filename,100,deadlines);
        }

        olimp.initEntities(5,4,filenames,listTimes);
    }
}
