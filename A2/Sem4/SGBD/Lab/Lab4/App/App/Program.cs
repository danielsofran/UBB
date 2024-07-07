
using System.Data.SqlClient;

internal class Program
{
    static volatile int fails = 0;
    const int NR_MAX = 5;
    const string connectionString = "Data Source=DESKTOP-GAQSC82; Initial Catalog=Sporturi; Integrated Security=True";

    static void Deadlock1()
    {
        Console.ForegroundColor = ConsoleColor.Green;
        Console.WriteLine($"Deadlock1 Started!");
        Console.ForegroundColor = ConsoleColor.White;
        try
        {
            using (var conn = new SqlConnection(connectionString))
            using (var command = new SqlCommand("Deadlock1", conn) { CommandType = System.Data.CommandType.StoredProcedure })
            {
                conn.Open();
                command.ExecuteNonQuery();

                Console.ForegroundColor = ConsoleColor.Green;
                Console.WriteLine($"Deadlock1 Finished!\nFails: {fails}");
                Console.ForegroundColor = ConsoleColor.White;
            }
        }
        catch (Exception ex)
        {
            fails++;
            Console.ForegroundColor = ConsoleColor.Red;
            Console.WriteLine($"Deadlock1 Failed!\nFails: {fails}");
            Console.ForegroundColor = ConsoleColor.White;

            if (fails > NR_MAX)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("Failed too many times.\nExecution aborted.");
                Console.ForegroundColor = ConsoleColor.White;
            }
            else {
                Console.WriteLine("Starting another thread...");
                Thread thread = new Thread(Deadlock1);
                thread.Start();
            }
        }
    }

    static void Deadlock2()
    {
        Console.ForegroundColor = ConsoleColor.Green;
        Console.WriteLine($"Deadlock2 Started!");
        Console.ForegroundColor = ConsoleColor.White;
        try
        {
            using (var conn = new SqlConnection(connectionString))
            using (var command = new SqlCommand("Deadlock2", conn) { CommandType = System.Data.CommandType.StoredProcedure })
            {
                conn.Open();
                command.ExecuteNonQuery();

                Console.ForegroundColor = ConsoleColor.Green;
                Console.WriteLine($"Deadlock2 Finished!\nFails: {fails}");
                Console.ForegroundColor = ConsoleColor.White;
            }
        }
        catch (Exception ex)
        {
            fails++;
            Console.ForegroundColor = ConsoleColor.Red;
            Console.WriteLine($"Deadlock2 Failed!\nFails: {fails}");
            Console.ForegroundColor = ConsoleColor.White;

            Console.WriteLine("Starting another thread...");
            Thread thread = new Thread(Deadlock1);
            thread.Start();
        }
    }

    private static void Main(string[] args)
    {
        Thread t1 = new Thread(Deadlock1);
        Thread t2 = new Thread(Deadlock2);

        t1.Start();
        t2.Start();
    }
}