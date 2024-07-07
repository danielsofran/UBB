using bilete.model;
using bilete.network.protocol;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace networking
{
    public class TestJson
    {
        public static void Main(string[] args)
        {
            JsonSerializerOptions serializerOptions = new JsonSerializerOptions()
            {
                WriteIndented = true,
                PropertyNamingPolicy = JsonNamingPolicy.CamelCase,
                Converters= { new JsonStringEnumConverter()}
            };
            Show show = new Show()
            {
                ID = 1,
                Artist = new Artist(1, "Andrei"),
                Date = DateOnly.FromDateTime(DateTime.Now),
                BeginTime = TimeOnly.FromDateTime(DateTime.Now),
                Location = "aici",
                AvailableSeats = 100,
                SoldSeats = 0
            };
            Show show1 = show;

            //Response response = new Response() { Type = ResponseType.OK, Data = show };

            String json = JsonSerializer.Serialize(show, serializerOptions);
            Console.WriteLine(json);
            Console.ReadLine();
            Show showD = JsonSerializer.Deserialize<Show>(json, serializerOptions);
            Console.WriteLine(showD);
        }
    }
}
