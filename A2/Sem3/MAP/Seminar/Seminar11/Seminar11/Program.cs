MessageTask m1 = new MessageTask("1", "teme laborator",
                "ai primit o tema de laborator", "George", "Maria",
                DateTime.Now);
MessageTask m2 = new MessageTask("2", "teme laborator",
        "ai o tema de seminar", "Mihai", "Maria",
        DateTime.Now);
MessageTask m3 = new MessageTask("3", "teme seminar",
        "ai primit o tema de seminar", "George", "MIhai",
        DateTime.Now);
MessageTask m4 = new MessageTask("4", "cerere bursa",
        "va rog depuneti cererea", "Secretariat", "Daniel",
        DateTime.Now);
MessageTask m5 = new MessageTask("5", "congrats",
        "you completed the 200km running challenge", "Strava @Inc", "Daniel",
        DateTime.Now);

List<Task> tasks= new List<Task>() { m1, m2, m3, m4, m5};
foreach(var task in tasks)
    task.execute();

