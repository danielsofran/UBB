using System;
using System.Collections.Generic;
using System.IO;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using DevizeBiciclete.Domain;
using Deviz = DevizeBiciclete.Domain.DevizData;
using Devize = System.Collections.Generic.List<DevizeBiciclete.Domain.DevizData>;

namespace DevizeBiciclete.Repo
{
    public class MyException : Exception
    {
        public MyException(){}
        public MyException(string message): base(message){}
        public MyException(string message, Exception inner): base(message, inner){}
    }

    public class RepositoryEventArgs : EventArgs
    {
        public DevizData Deviz = null;
        public RepositoryEventArgs(DevizData deviz){ Deviz = deviz; }
    }

    public delegate void RepositoryEventHandler(object sender, RepositoryEventArgs e);

    public class Repository
    {
        public event EventHandler ItemsChanged; 
        public event RepositoryEventHandler ItemsAdded;

        Devize devize = new Devize();
        string path="";
        static int operations_count = 0;
        static int operation_limit_nosave = 3;
        long lastnumar = 0;

        public void Add(DevizData devizData)
        {
            if (devize.Contains(devizData)) { MessageBox.Show("Deviz duplicat!"); return; }
            devize.Add(devizData);
            if (devizData.Numar > lastnumar) lastnumar = devizData.Numar;
            operations_count++;
            if(operations_count % operation_limit_nosave == 0) ToFile();
            ItemsAdded?.Invoke(this, new RepositoryEventArgs(devizData));
            ItemsChanged?.Invoke(this, EventArgs.Empty);
        }

        public void Remove(DevizData devizData, bool all = false) // sterg primul elem
        {
            for (int i = 0; i < devize.Count; i++)
                if (devize[i] == devizData)
                {
                    devize.RemoveAt(i);
                    i--; // practic cu i++ raman pe aceeasi pozitie
                    operations_count++;
                    if (operations_count % operation_limit_nosave == 0) ToFile();
                    if (!all)
                    {
                        ItemsChanged?.Invoke(this, EventArgs.Empty);
                        return;
                    }
                }
            ItemsChanged?.Invoke(this, EventArgs.Empty);
        }

        public void Remove(Func<DevizData, bool> fnc, bool all=false)  // sterg toate elem care respecta fct
        {
            for (int i = 0; i < devize.Count; i++)
                if (fnc(devize[i]))
                {
                    devize.RemoveAt(i);
                    i--; // practic cu i++ raman pe aceeasi pozitie
                    operations_count++;
                    if (operations_count % operation_limit_nosave == 0) ToFile();
                    if (!all)
                    {
                        ItemsChanged?.Invoke(this, EventArgs.Empty);
                        return;
                    }
                }
            ItemsChanged?.Invoke(this, EventArgs.Empty);
        }

        public bool Contains(Deviz deviz)
        {
            foreach (DevizData devizData in devize)
                if (devizData == deviz)
                    return true;
            return false;
        }

        public Devize Find(Func<DevizData, bool> fnc)
        {
            Devize devize = new Devize();
            foreach(DevizData devizData in devize)
                if(fnc(devizData))
                    devize.Add(devizData);
            return devize;
        }

        public void MergeWith(Repository repo)
        {
            foreach (DevizData deviz in repo.ToList)
                if (!Contains(deviz))
                {
                    devize.Add(deviz);
                    if (deviz.Numar > lastnumar) lastnumar = deviz.Numar;
                    ItemsAdded?.Invoke(this, new RepositoryEventArgs(deviz));
                }
            operations_count++;
            if (operations_count % operation_limit_nosave == 0) ToFile();
            ItemsChanged?.Invoke(this, EventArgs.Empty);
        }

        public string Path { 
            get{ 
                return path; 
            } 
            set {
                path = value;
            } 
        }
        public Devize ToList { get => devize; }
        public long LastNumar { get => lastnumar; }

        public override string ToString()
        {
            string rez="", n=Environment.NewLine;
            foreach (DevizData deviz in devize)
                rez += deviz + n;
            return rez;
        }

        public void ToFile(string rpath = null)
        {
            if (rpath == null) rpath = path;
            using(StreamWriter sw = new StreamWriter(rpath))
                sw.Write(ToString());
        }

        public void Reload()
        {
            devize.Clear();
            using (StreamReader sr = new StreamReader(path))
            {
                string line;
                while ((line = sr.ReadLine()) is not null)
                    if ((line is not null) && line != "")
                    {
                        devize.Add(DevizData.FromString(line));
                    }
            }
            ItemsChanged?.Invoke(this, EventArgs.Empty);
        }

        public static Repository FromFile(string path)
        {
            Repository repository = new Repository();
            repository.path = path;
            using(StreamReader sr = new StreamReader(path))
            {
                string line;
                while((line = sr.ReadLine()) is not null)
                    if((line is not null) && line != "")
                    {
                        Deviz deviz = DevizData.FromString(line);
                        if(deviz.Numar > repository.lastnumar)
                            repository.lastnumar = deviz.Numar;
                        repository.devize.Add(deviz);
                    }
            }
            return repository;
        }
    }
}
