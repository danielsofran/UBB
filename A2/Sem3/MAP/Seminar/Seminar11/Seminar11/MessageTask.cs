abstract class Task
{
    public string taskID { get; set; }
    public string Descriere { get; set; }

    public Task(string taskID, string descriere)
    {
        this.taskID = taskID;
    }

    public abstract void execute();

    public override string ToString()
    {
        return taskID + " " + Descriere;
    }


    public override int GetHashCode()
    {
        return taskID.GetHashCode();
    }
}

class MessageTask : Task
{
    public string mesaj { get; set; }
    public string from { get; set; }
    public string to { get; set; }
    public DateTime Date { get; set; }

    public MessageTask(string taskid, string descriere, string mesaj, string from, string to, DateTime date) : base(taskid, descriere)
    {
        this.Date = date;
        this.from= from;
        this.to= to;
        this.mesaj = mesaj;
    }

    public override void execute()
    {
        Console.WriteLine(this.mesaj + "(" + this.Date.ToString("dd.MM.yyyy hh:mm") + ")");
    }
}

