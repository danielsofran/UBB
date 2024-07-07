using Lab8MAP.domain;
using Lab8MAP.repository;
using Lab8MAP.utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab8MAP.tests
{
    internal static class Test
    {
        static void testRepo <ID, E>(AbstractDBRepository<ID, E> repository) where ID : IComparable<ID> where E : Entity<ID>
        {
            MessageBox.Show(repository.TableName + " " + repository.FindAll().Count.ToString());
        }

        static void testDBConn()
        {
            //AbstractDBRepository arepo = new();
            //FileToDB fileToDB = new FileToDB();
            //fileToDB.InsertData();
            AbstractDBRepository<int, Echipa<int>> repoEchipe = new RepoEchipe();
            testRepo(repoEchipe);
            AbstractDBRepository<int, Elev<int>> repoElevi = new RepoElevi();
            testRepo(repoElevi);
            AbstractDBRepository<int, Jucator<int>> repoJucatori = new RepoJucatori();
            testRepo(repoJucatori);
            AbstractDBRepository<int, Meci<int>> repoMeci = new RepoMeci();
            testRepo(repoMeci);
            AbstractDBRepository<int, JucatorActiv<int>> repoJucatoriActivi = new RepoJucatoriActivi();
            testRepo(repoJucatoriActivi);
        }

        public static void RunTests()
        {
            //testDBConn();
        }
    }
}
