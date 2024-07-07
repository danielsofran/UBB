using Microsoft.CSharp;
using System;
using System.CodeDom.Compiler;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PDFtoTXT
{
    internal class CodeCompiler
    {
        public static string GetCode()
        {
            return File.ReadAllText("code.txt");
        }

        public static void CreateProgramCS(string textcs)
        {
            CSharpCodeProvider codeProvider = new CSharpCodeProvider();
            ICodeCompiler icc = codeProvider.CreateCompiler();
            string Output = "mypdftotxt.exe";

            System.CodeDom.Compiler.CompilerParameters parameters = new CompilerParameters(new[] { "Aspose.PDF.dll", "Aspose.Words.dll", "Aspose.Words.Pdf2Word.dll" }, Output, true);
            //Make sure we generate an EXE, not a DLL
            parameters.GenerateExecutable = true;
            parameters.OutputAssembly = Output;
            CompilerResults results = icc.CompileAssemblyFromSource(parameters, textcs);

            if (results.Errors.Count > 0)
            {
                foreach (CompilerError CompErr in results.Errors)
                {
                    MessageBox.Show(
                                "Line number " + CompErr.Line +
                                "\n Error Number: " + CompErr.ErrorNumber +
                                "\n '" + CompErr.ErrorText + ";" +
                                Environment.NewLine + Environment.NewLine);
                }
            }
            else
            {
                //Successful Compile
                MessageBox.Show("Success!");
            }
        }

        public static void CreateProgramFromSource()
        {
            string outputpath = Application.StartupPath + @"\pdfexe";
            ProcessStartInfo ps = new ProcessStartInfo("dotnet", string.Format(@"build pdftotxtexe\ -c Release -o {0}", outputpath));
            ps.RedirectStandardOutput = true;
            ps.UseShellExecute = false;
            Process.Start(ps);
        }

        public static void RunProgram(string pathin, string pathout, int nr)
        {
            string outputpath = Application.StartupPath + @"\pdfexe";
            if (!Directory.Exists(outputpath))
                CreateProgramFromSource();
            string progrpath = Application.StartupPath + @"pdfexe\pdftotxtexe.exe";
            ProcessStartInfo ps = new ProcessStartInfo(progrpath, string.Format("{0} {1} {2}", pathin, pathout, nr));
            ps.RedirectStandardOutput = true;
            ps.UseShellExecute = false;
            var p = Process.Start(ps);
            p.WaitForExit();
        }
    }
}
