using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExPractic1
{
    internal interface ParentChildTableApp
    {
        string ConnectionString { get; }
        string ParentTableName { get; }
        string ChildTableName { get; }
        string ParentPKColName { get; }
        string ChildFKColName { get; }
        string ChildPKColName { get; }
        void init();
    }
}
