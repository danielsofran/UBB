using bilete.model;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Net.Http.Headers;
using System.Text.Json.Nodes;
using System.Text;

namespace client_http
{
    internal class Program
    {
        static HttpClient client = new HttpClient();
        static string path = "http://localhost:8080/artists";
        static HttpContent content;

        private static HttpContent createJsonContent(string json)
            => new StringContent(json, Encoding.UTF8, "application/json");

        public static void Main(string[] args)
        {
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
            Test().Wait();
            Console.WriteLine("End");
        }

        private static async Task Test()
        {
            Console.WriteLine("Hello");
            Artist artist = await getArtist(4);
            Console.WriteLine(artist);
            List<Artist> artists = await getAllArtists();
            artists.ForEach(artist => Console.WriteLine(artist));
            Artist added = await addArtist(new Artist(0, "C#"));
            Console.WriteLine(added);
            Artist updated = await updateArtist(added.ID, new Artist(0, "Java"));
            Console.WriteLine("Updated: "+updated);
            await deleteArtist(updated.ID);
        }

        private static async Task<Artist> getArtist(int id)
        {
            HttpResponseMessage httpResponseMessage= await client.GetAsync(path + "/"+id);
            if(httpResponseMessage.IsSuccessStatusCode)
            {
                String json = await httpResponseMessage.Content.ReadAsStringAsync();
                Artist? artist = JsonConvert.DeserializeObject<Artist>(json);
                return artist;
            }
            throw new Exception("Not found");
        }

        private static async Task<List<Artist>> getAllArtists()
        {
            HttpResponseMessage httpResponseMessage = await client.GetAsync(path);
            if (httpResponseMessage.IsSuccessStatusCode)
            {
                String json = await httpResponseMessage.Content.ReadAsStringAsync();
                return JsonConvert.DeserializeObject<List<Artist>>(json);
            }
            throw new Exception("Not found");
        }

        private static async Task<Artist> addArtist(Artist artist)
        {
            var json = JsonConvert.SerializeObject(artist);
            HttpResponseMessage httpResponseMessage = await client.PostAsync(path, createJsonContent(json));
            if (httpResponseMessage.IsSuccessStatusCode)
            {
                json = await httpResponseMessage.Content.ReadAsStringAsync();
                return JsonConvert.DeserializeObject<Artist>(json);
            }
            throw new Exception("Not found");
        }

        private static async Task deleteArtist(int id)
        {
            Console.WriteLine("Delete: ");
            HttpResponseMessage httpResponseMessage = await client.DeleteAsync(path+"/"+id);
            if (httpResponseMessage.IsSuccessStatusCode)
            {
                Console.ForegroundColor= ConsoleColor.Green;
                Console.WriteLine("Succes!");
                Console.ForegroundColor = ConsoleColor.White;
                return;
            }
            Console.ForegroundColor = ConsoleColor.Red;
            Console.WriteLine("Nu a fost gasit!");
            Console.ForegroundColor = ConsoleColor.White;
        }

        private static async Task<Artist> updateArtist(int id, Artist artist)
        {
            var json = JsonConvert.SerializeObject(artist);
            HttpResponseMessage httpResponseMessage = await client.PutAsync(path+"/"+id, createJsonContent(json));
            if (httpResponseMessage.IsSuccessStatusCode)
            {
                json = await httpResponseMessage.Content.ReadAsStringAsync();
                return JsonConvert.DeserializeObject<Artist>(json);
            }
            throw new Exception("Not found");
        }
    }
}