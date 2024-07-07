using Aspose.Pdf;
using Aspose.Pdf.Devices;
using Aspose.Pdf.Text;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HorizontalAlignment = Aspose.Pdf.HorizontalAlignment;

namespace DevizeBiciclete.Domain
{
    public static partial class ExportPdf
    {
        public static void ToPDF(this DevizData devizData, string path, int entersPM=1, int entersMT=1)
        {
            Document doc = new Document();
            MarginInfo marginPage = new MarginInfo(40, 20, 20, 40);
            doc.PageInfo.Margin = marginPage;
            
            Page page = doc.Pages.Add();
            page.Header = new HeaderFooter();
            page.Header.Margin = new MarginInfo(10, 10, 10, 50);

            #region Header
            Table headerTable = new Table();
            headerTable.ColumnWidths = "250 250 250";
            headerTable.ColumnAdjustment = ColumnAdjustment.AutoFitToWindow;
            headerTable.DefaultCellPadding = new MarginInfo(10, 0, 10, 0);
            headerTable.CornerStyle = BorderCornerStyle.None;
            headerTable.VerticalAlignment = VerticalAlignment.Top;
            Row row = headerTable.Rows.Add();
            row.VerticalAlignment = VerticalAlignment.Center;

            Cell celllogo = row.Cells.Add();
            celllogo.Alignment = Aspose.Pdf.HorizontalAlignment.Left;
            celllogo.VerticalAlignment = VerticalAlignment.Top;
            Aspose.Pdf.Image logo = new Aspose.Pdf.Image();
            logo.File = "logo.png";
            logo.FixWidth = 105;
            logo.FixHeight = 60;
            celllogo.Paragraphs.Add(logo);

            Cell cellTITLE = row.Cells.Add();
            cellTITLE.Alignment = Aspose.Pdf.HorizontalAlignment.Center;
            cellTITLE.VerticalAlignment = VerticalAlignment.Center;
            TextFragment fragment = new TextFragment(DevizSetari.Titlu.FaraDiacritice() + " ");
            fragment.TextState.Font = FontRepository.FindFont("Calibri");
            fragment.TextState.FontSize = 48;
            cellTITLE.Paragraphs.Add(fragment);

            Cell cellDr = row.Cells.Add();
            cellDr.Alignment = Aspose.Pdf.HorizontalAlignment.Right;
            cellDr.VerticalAlignment = VerticalAlignment.Top;
            string n = Environment.NewLine;
            string stsus = "Deviz Nr. " + devizData.Numar.ToString() + n
                            + "Data intrării în service: " + devizData.Constatare.DataInText + n
                            + "Data ieșirii din service: " + devizData.Constatare.DataOutText;
            fragment = new TextFragment(stsus);
            fragment.TextState.Font = FontRepository.FindFont("Calibri");
            fragment.TextState.FontSize = 11;
            cellDr.Paragraphs.Add(fragment);

            headerTable.Margin.Top += 10;
            headerTable.Margin.Left -= 20;
            headerTable.Margin.Right -= 20;
            headerTable.Margin.Bottom += 0;
            page.Paragraphs.Add(headerTable);
            #endregion

            #region Cilent Service

            Table table;
            Cell cellst, celldr, cellmij;
            initTable3(out table, out cellst, out celldr, out cellmij);
            table.Margin.Top += 30;

            TextFragment fragmentSubtitlu;
            initFragmentSubtitlu(out fragmentSubtitlu);
            fragmentSubtitlu.Text = DevizSetari.Service.Titlu.FaraDiacritice();
            cellst.Paragraphs.Add(fragmentSubtitlu);
            initFragmentSubtitlu(out fragmentSubtitlu);
            fragmentSubtitlu.Text = "Client";
            cellmij.Paragraphs.Add(fragmentSubtitlu);
            initFragmentSubtitlu(out fragmentSubtitlu);
            fragmentSubtitlu.Text = "Bicicleta";
            celldr.Paragraphs.Add(fragmentSubtitlu);

            // CELL ST

            fragment = new TextFragment();

            TextSegment segmentTextRegular, segmentTextBold;
            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
            segmentTextRegular.Text = DevizSetari.Service.Titlu.FaraDiacritice() + ": ";
            //fragment.Segments.Add(segmentTextRegular);
            segmentTextBold.Text = DevizSetari.Service.Nume.FaraDiacritice() + n;
            fragment.Segments.Add(segmentTextBold);

            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
            segmentTextRegular.Text = "C.I.F. ";
            segmentTextBold.Text = DevizSetari.Service.RO.FaraDiacritice() + n;
            fragment.Segments.Add(segmentTextRegular);
            fragment.Segments.Add(segmentTextBold);

            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
            segmentTextRegular.Text = "Nr. Reg. Com. ";
            segmentTextBold.Text = DevizSetari.Service.Registru.FaraDiacritice() + n;
            fragment.Segments.Add(segmentTextRegular);
            fragment.Segments.Add(segmentTextBold);

            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
            segmentTextRegular.Text = "Aresa: ";
            segmentTextBold.Text = DevizSetari.Service.Adresa.FaraDiacritice() + n;
            //fragment.Segments.Add(segmentTextRegular);
            fragment.Segments.Add(segmentTextBold);

            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
            segmentTextRegular.Text = "Telefon: ";
            segmentTextBold.Text = DevizSetari.Service.Telefon.FaraDiacritice() + n;
            fragment.Segments.Add(segmentTextRegular);
            fragment.Segments.Add(segmentTextBold);

            cellst.Paragraphs.Add(fragment);

            // CELL MIJ

            fragment = new TextFragment();

            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
            segmentTextRegular.Text = "Nume: ";
            fragment.Segments.Add(segmentTextRegular);
            segmentTextBold.Text = devizData.Client.Nume.FaraDiacritice() + n;
            fragment.Segments.Add(segmentTextBold);

            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
            segmentTextRegular.Text = "Telefon: ";
            fragment.Segments.Add(segmentTextRegular);
            segmentTextBold.Text = devizData.Client.Telefon.FaraDiacritice() + n;
            fragment.Segments.Add(segmentTextBold);

            if (devizData.Client.PersoanaJuridica)
            {
                initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
                segmentTextRegular.Text = "Denumire: ";
                fragment.Segments.Add(segmentTextRegular);
                segmentTextBold.Text = devizData.Client.Denumire.FaraDiacritice() + n;
                fragment.Segments.Add(segmentTextBold);

                initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
                segmentTextRegular.Text = "C.I.F. ";
                fragment.Segments.Add(segmentTextRegular);
                segmentTextBold.Text = devizData.Client.RO.FaraDiacritice() + n;
                fragment.Segments.Add(segmentTextBold);

                initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
                segmentTextRegular.Text = "Nr. Reg. Com. ";
                fragment.Segments.Add(segmentTextRegular);
                segmentTextBold.Text = devizData.Client.Registru.FaraDiacritice() + n;
                fragment.Segments.Add(segmentTextBold);

                initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
                segmentTextRegular.Text = "Adresa: ";
                fragment.Segments.Add(segmentTextRegular);
                segmentTextBold.Text = devizData.Client.Adresa.FaraDiacritice() + n;
                fragment.Segments.Add(segmentTextBold);

                initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
                segmentTextRegular.Text = "Telefon: ";
                fragment.Segments.Add(segmentTextRegular);
                segmentTextBold.Text = devizData.Client.TelefonFrima.FaraDiacritice() + n; ////////////////////////////////////////// tel firma
                fragment.Segments.Add(segmentTextBold);
            }

            cellmij.Paragraphs.Add(fragment);

            // CELL DR

            fragment = new TextFragment();

            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
            segmentTextRegular.Text = "Marca: ";
            fragment.Segments.Add(segmentTextRegular);
            segmentTextBold.Text = devizData.Bicicleta.Marca.FaraDiacritice() + n;
            fragment.Segments.Add(segmentTextBold);

            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
            segmentTextRegular.Text = "Model: ";
            fragment.Segments.Add(segmentTextRegular);
            segmentTextBold.Text = devizData.Bicicleta.Model.FaraDiacritice() + n;
            fragment.Segments.Add(segmentTextBold);

            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
            segmentTextRegular.Text = "Culoare: ";
            fragment.Segments.Add(segmentTextRegular);
            segmentTextBold.Text = devizData.Bicicleta.Culoare.FaraDiacritice() + n;
            fragment.Segments.Add(segmentTextBold);

            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
            segmentTextRegular.Text = "Serie: ";
            fragment.Segments.Add(segmentTextRegular);
            segmentTextBold.Text = devizData.Bicicleta.Serie.FaraDiacritice() + n;
            fragment.Segments.Add(segmentTextBold);

            celldr.Paragraphs.Add(fragment);

            page.Paragraphs.Add(table);
            #endregion

            #region Constatare

            initFragmentSubtitlu(out fragmentSubtitlu);
            fragmentSubtitlu.Text = "Constatare";
            page.Paragraphs.Add(fragmentSubtitlu);

            initFragmentText(out fragment);

            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold, "Motivul intrarii in service: ", devizData.Constatare.Motiv + n);
            fragment.Segments.Add(segmentTextRegular);
            fragment.Segments.Add(segmentTextBold);

            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
            segmentTextRegular.Text = "Data intrarii in service: ";
            fragment.Segments.Add(segmentTextRegular);
            segmentTextBold.Text = devizData.Constatare.DataInText + n;
            fragment.Segments.Add(segmentTextBold);

            initSegmentRegularBold(out segmentTextRegular, out segmentTextBold);
            segmentTextRegular.Text = "Data iesirii din service: ";
            fragment.Segments.Add(segmentTextRegular);
            segmentTextBold.Text = devizData.Constatare.DataOutText + n;
            fragment.Segments.Add(segmentTextBold);

            page.Paragraphs.Add(fragment);

            #endregion

            #region Piese

            initFragmentSubtitlu(out fragment);
            fragment.Text = "Piese";
            fragment.Margin.Top += 10;
            page.Paragraphs.Add(fragment);

            table = new Table();
            table.DefaultCellBorder = new BorderInfo(BorderSide.All, .55f, Aspose.Pdf.Color.Black);
            table.DefaultCellPadding = new MarginInfo(3, 3, 3, 3);
            table.Margin = new MarginInfo(10, 5, 10, 0);
            table.CornerStyle = BorderCornerStyle.Round;
            table.VerticalAlignment = VerticalAlignment.Center;
            table.HorizontalAlignment = Aspose.Pdf.HorizontalAlignment.Center;
            table.ColumnWidths = "30 60 250 30 60 65";

            initTableHearder(table, "Nr.", "Cod piesa", "Nume Piesa", "Nr. buc.", "Pret (RON)", "Pret Total (RON)");

            //for(int __i__ =1; __i__ < 10; ++__i__)
            for (int i = 0; i < devizData.Piese.Count; i++)
                addRowCellsPiese(table, devizData.Piese[i], i + 1);

            if (devizData.Piese.Count == 0)
                addRowCellsPiese(table, new DevizData.PiesaData(), 0);

            page.Paragraphs.Add(table);
            #endregion

            #region Manopere

            initFragmentSubtitlu(out fragment);
            fragment.Text = "Manopera";
            fragment.Margin.Top += 10;
            //page.Paragraphs.Add(fragment);

            table = new Table();
            table.DefaultCellBorder = new BorderInfo(BorderSide.All, .55f, Aspose.Pdf.Color.Black);
            table.DefaultCellPadding = new MarginInfo(3, 3, 3, 3);
            table.Margin = new MarginInfo(10, 5, 10, 0);
            table.CornerStyle = BorderCornerStyle.None;
            table.VerticalAlignment = VerticalAlignment.Center;
            table.HorizontalAlignment = Aspose.Pdf.HorizontalAlignment.Center;
            table.ColumnWidths = "30 240 50 60 50 65";

            initTableHearder(table, "Nr.", "Manopera", "Durata (ore)", "Pret (RON)", "Discount", "Pret Total (RON)");

            if(entersPM>0) // automat?
            {
                TextFragment enterp = new TextFragment(n);
                enterp.TextState.FontSize = fragment.TextState.FontSize;
                for(int i=1;i<=entersPM;++i) page.Paragraphs.Add(enterp);
            }
            page.Paragraphs.Add(fragment);

            for (int i = 0; i < devizData.Manopere.Count; i++)
                addRowCellsManopera(table, devizData.Manopere[i], i + 1);
            if (devizData.Manopere.Count == 0)
                addRowCellsManopera(table, new DevizData.ManoperaData(), 0);

            page.Paragraphs.Add(table);

            if (entersMT > 0) // automat?
            {
                TextFragment enterp = new TextFragment(n);
                enterp.TextState.FontSize = fragment.TextState.FontSize;
                for (int i = 1; i <= entersMT; ++i) page.Paragraphs.Add(enterp);
            }

            #endregion

            #region Total

            table = new Table();
            table.DefaultCellBorder = new BorderInfo(BorderSide.All, .55f, Aspose.Pdf.Color.Black);
            table.DefaultCellPadding = new MarginInfo(3, 3, 3, 3);
            table.Margin = new MarginInfo(100, 5, 10, 30);
            table.CornerStyle = BorderCornerStyle.None;
            table.VerticalAlignment = VerticalAlignment.Center;
            table.HorizontalAlignment = Aspose.Pdf.HorizontalAlignment.Center;
            table.ColumnWidths = "30 240 80";

            initTableHearder(table, "Nr.", "Total", "Pret (RON)");

            addRowCellsTotal(table, 1, "Total Piese", devizData.TotalPiese);
            addRowCellsTotal(table, 2, "Total Manopera", devizData.TotalManopera);
            addRowCellsTotal(table, 3, "TOTAL DEVIZ FARA TVA", devizData.TotalFaraTVA, true);
            addRowCellsTotal(table, 4, "TVA", devizData.TVAdinTotal, true);
            addRowCellsTotal(table, 5, "TOTAL DEVIZ CU TVA", devizData.TotalCuTVA, true);

            page.Paragraphs.Add(table);

            #endregion

            #region Semnaturi

            initTable(out table, out cellst, out celldr);
            table.Margin.Top += 30;
            table.Margin.Left -= 30;

            initFragmentSubtitlu(out fragmentSubtitlu);
            fragmentSubtitlu.Text = "Semnatura furnizor";
            fragmentSubtitlu.TextState.FontSize = 18;
            cellst.Paragraphs.Add(fragmentSubtitlu);
            initFragmentSubtitlu(out fragmentSubtitlu);
            fragmentSubtitlu.Text = "Semnatura client";
            fragmentSubtitlu.TextState.FontSize = 18;
            celldr.Paragraphs.Add(fragmentSubtitlu);

            initFragmentSubtitlu(out fragmentSubtitlu);
            fragmentSubtitlu.Text = DevizSetari.Service.Nume.FaraDiacritice();
            fragmentSubtitlu.TextState.FontSize = 18;
            fragmentSubtitlu.TextState.FontStyle = FontStyles.Regular;
            fragmentSubtitlu.Margin.Top = 0;
            cellst.Paragraphs.Add(fragmentSubtitlu);
            initFragmentSubtitlu(out fragmentSubtitlu);
            fragmentSubtitlu.Text = devizData.Client.Nume.FaraDiacritice();
            fragmentSubtitlu.TextState.FontSize = 18;
            fragmentSubtitlu.TextState.FontStyle = FontStyles.Regular;
            fragmentSubtitlu.Margin.Top = 0;
            celldr.Paragraphs.Add(fragmentSubtitlu);

            page.Paragraphs.Add(table);

            #endregion

            doc.Save(path);
        }
        
        private static void initSegmentRegularBold(out TextSegment segmentTextRegular, out TextSegment segmentTextBold, string textRegular="", string textBold="")
        {
            segmentTextRegular = new TextSegment();
            segmentTextRegular.TextState.Font = FontRepository.FindFont("Calibri");
            segmentTextRegular.TextState.FontSize = 16;
            segmentTextRegular.TextState.LineSpacing = 1.2f;
            segmentTextRegular.Text = textRegular.FaraDiacritice();

            segmentTextBold = new TextSegment();
            segmentTextBold.TextState.Font = segmentTextRegular.TextState.Font;
            segmentTextBold.TextState.FontSize = segmentTextRegular.TextState.FontSize;
            segmentTextBold.TextState.LineSpacing = segmentTextRegular.TextState.LineSpacing;
            segmentTextBold.TextState.FontStyle = FontStyles.Bold;
            segmentTextBold.Text = textBold.FaraDiacritice();
        }
        
        private static void initFragmentSubtitlu(out TextFragment fragmentSubtitlu)
        {
            fragmentSubtitlu = new TextFragment();
            fragmentSubtitlu.TextState.Font = FontRepository.FindFont("Calibri");
            fragmentSubtitlu.TextState.FontSize = 24;
            fragmentSubtitlu.TextState.FontStyle = FontStyles.Bold;
            fragmentSubtitlu.TextState.LineSpacing = 2;
            fragmentSubtitlu.TextState.HorizontalAlignment = Aspose.Pdf.HorizontalAlignment.Center;
            fragmentSubtitlu.HorizontalAlignment = Aspose.Pdf.HorizontalAlignment.Center;
            fragmentSubtitlu.Margin = new MarginInfo(0, 12, 0, 20);
        }
        
        private static void initFragmentSubtitluSt(out TextFragment fragmentSubtitlu)
        {
            initFragmentSubtitlu(out fragmentSubtitlu);
            fragmentSubtitlu.HorizontalAlignment= Aspose.Pdf.HorizontalAlignment.Left;
            fragmentSubtitlu.Margin= new MarginInfo(120, fragmentSubtitlu.Margin.Bottom, 0, fragmentSubtitlu.Margin.Top);
        }

        private static void initFragmentText(out TextFragment fragment)
        {
            fragment = new TextFragment();
            fragment.Margin = new MarginInfo(65, 0, 55, 5);
        }
    
        private static void initTable(out Table tableClSv, out Cell cellst, out Cell celldr, bool borders=false)
        {
            tableClSv = new Table();
            //tableClSv.BackgroundColor = Aspose.Pdf.Color.Black;
            tableClSv.ColumnWidths = "250 250";
            tableClSv.ColumnAdjustment = ColumnAdjustment.AutoFitToWindow;
            tableClSv.DefaultCellPadding = new MarginInfo(15, 0, 15, 0);
            int marginx = 30;
            tableClSv.Margin = new MarginInfo(marginx + 20, 5, marginx, 0);
            tableClSv.CornerStyle = BorderCornerStyle.None;
            tableClSv.VerticalAlignment = VerticalAlignment.Center;
            tableClSv.HorizontalAlignment = Aspose.Pdf.HorizontalAlignment.Center;
            Row row = tableClSv.Rows.Add();
            row.VerticalAlignment = VerticalAlignment.Center;
            cellst = row.Cells.Add();
            cellst.VerticalAlignment = VerticalAlignment.Top;
            celldr = row.Cells.Add();
            celldr.VerticalAlignment = VerticalAlignment.Top;
            if(borders)
            {
                //tableClSv.Border = new BorderInfo(Aspose.Pdf.BorderSide.All, .55f, Aspose.Pdf.Color.FromRgb(System.Drawing.Color.Black));
                // Set the border for table cells
                tableClSv.DefaultCellBorder = new BorderInfo(Aspose.Pdf.BorderSide.All, .55f, Aspose.Pdf.Color.FromRgb(System.Drawing.Color.Black));
            }
        }

        private static void initTable3(out Table tableClSv, out Cell cellst, out Cell celldr, out Cell cellmij, bool borders = false)
        {
            tableClSv = new Table();
            //tableClSv.BackgroundColor = Aspose.Pdf.Color.Black;
            tableClSv.ColumnWidths = "200 200 20";
            tableClSv.ColumnAdjustment = ColumnAdjustment.AutoFitToWindow;
            tableClSv.DefaultCellPadding = new MarginInfo(2, 0, 2, 0);
            int marginx = 10;
            tableClSv.Margin = new MarginInfo(marginx, 5, marginx, 0);
            tableClSv.CornerStyle = BorderCornerStyle.None;
            tableClSv.VerticalAlignment = VerticalAlignment.Center;
            tableClSv.HorizontalAlignment = Aspose.Pdf.HorizontalAlignment.Center;
            Row row = tableClSv.Rows.Add();
            row.VerticalAlignment = VerticalAlignment.Center;
            cellst = row.Cells.Add();
            cellst.VerticalAlignment = VerticalAlignment.Top;
            cellmij = row.Cells.Add();
            cellmij.VerticalAlignment = VerticalAlignment.Top;
            celldr = row.Cells.Add();
            celldr.VerticalAlignment = VerticalAlignment.Top;
            if (borders)
            {
                //tableClSv.Border = new BorderInfo(Aspose.Pdf.BorderSide.All, .55f, Aspose.Pdf.Color.FromRgb(System.Drawing.Color.Black));
                // Set the border for table cells
                tableClSv.DefaultCellBorder = new BorderInfo(Aspose.Pdf.BorderSide.All, .55f, Aspose.Pdf.Color.FromRgb(System.Drawing.Color.Black));
            }
        }

        private static void initTableHearder(Table table, params string[] colnames)
        {
            Row row = table.Rows.Add();
            TextFragment fragment = new TextFragment();
            foreach(string colname in colnames)
            {
                Cell cell = row.Cells.Add();
                initTableContent(out fragment, colname.FaraDiacritice());
                cell.Paragraphs.Add(fragment);
            }
        }

        private static void initTableContent(out TextFragment fragment, string content, bool bold = false, int fontsize=11)
        {
            fragment = new TextFragment();
            fragment.TextState.Font = FontRepository.FindFont("Calibri");
            fragment.TextState.FontSize = fontsize;
            fragment.HorizontalAlignment = HorizontalAlignment.Center;
            fragment.VerticalAlignment = VerticalAlignment.Center;
            if(bold) fragment.TextState.FontStyle = FontStyles.Bold;
            fragment.Text = content.FaraDiacritice();
        }

        private static void addRowCellsPiese(Table table, DevizData.PiesaData piesa, int index) 
        {
            TextFragment fragment = new TextFragment();
            Row row = table.Rows.Add();
            Cell cellindex = row.Cells.Add(), cellcod = row.Cells.Add(), cellnume = row.Cells.Add(), cellnr = row.Cells.Add(), cellpret = row.Cells.Add(), celltotal = row.Cells.Add();
            if (index == 0)
            {
                initTableContent(out fragment, "-");
                cellindex.Paragraphs.Add(fragment);
                initTableContent(out fragment, "-");
                cellcod.Paragraphs.Add(fragment);
                initTableContent(out fragment, "-");
                cellnume.Paragraphs.Add(fragment);
                initTableContent(out fragment, "-");
                cellnr.Paragraphs.Add(fragment);
                initTableContent(out fragment, "-");
                cellpret.Paragraphs.Add(fragment);
                initTableContent(out fragment, "-");
                celltotal.Paragraphs.Add(fragment);
                return;
            }
            initTableContent(out fragment, index.ToString());
            cellindex.Paragraphs.Add(fragment);
            initTableContent(out fragment, piesa.Cod.FaraDiacritice());
            cellcod.Paragraphs.Add(fragment);
            initTableContent(out fragment, piesa.Nume.FaraDiacritice());
            cellnume.Paragraphs.Add(fragment);
            initTableContent(out fragment, string.Format("{0}", piesa.NrBuc));
            cellnr.Paragraphs.Add(fragment);
            initTableContent(out fragment, string.Format("{0:0.00}", piesa.Pret));
            cellpret.Paragraphs.Add(fragment);
            initTableContent(out fragment, string.Format("{0:0.00}", piesa.PretTotal));
            celltotal.Paragraphs.Add(fragment);
        }

        private static void addRowCellsManopera(Table table, DevizData.ManoperaData manopera, int index)
        {
            TextFragment fragment = new TextFragment();
            Row row = table.Rows.Add();
            Cell cellindex = row.Cells.Add(), cellnume = row.Cells.Add(), celldur = row.Cells.Add(), cellpret = row.Cells.Add(), celldisc = row.Cells.Add(), celltotal = row.Cells.Add();
            if(index == 0)
            {
                initTableContent(out fragment, "-");
                cellindex.Paragraphs.Add(fragment);
                initTableContent(out fragment, "-");
                celldur.Paragraphs.Add(fragment);
                initTableContent(out fragment, "-");
                cellnume.Paragraphs.Add(fragment);
                initTableContent(out fragment, "-");
                celldisc.Paragraphs.Add(fragment);
                initTableContent(out fragment, "-");
                cellpret.Paragraphs.Add(fragment);
                initTableContent(out fragment, "-");
                celltotal.Paragraphs.Add(fragment);
                return;
            }
            initTableContent(out fragment, index.ToString());
            cellindex.Paragraphs.Add(fragment);
            initTableContent(out fragment, manopera.Nume.FaraDiacritice());
            cellnume.Paragraphs.Add(fragment);
            initTableContent(out fragment, string.Format("{0:0.00}", manopera.Durata));
            celldur.Paragraphs.Add(fragment);
            initTableContent(out fragment, string.Format("{0:0.00}", manopera.Pret));
            cellpret.Paragraphs.Add(fragment);
            initTableContent(out fragment, string.Format("{0:0.00}%", manopera.Discount));
            if (manopera.Discount == 0) fragment.Text = "-";
            celldisc.Paragraphs.Add(fragment);
            initTableContent(out fragment, string.Format("{0:0.00}", manopera.PretTotal));
            celltotal.Paragraphs.Add(fragment);
        }
        
        private static void addRowCellsTotal(Table table, int index, string titlu, float value, bool bold=false)
        {
            TextFragment fragment;
            Row row = table.Rows.Add();
            Cell cellst = row.Cells.Add(), cellmij = row.Cells.Add(), celldr = row.Cells.Add();
            initTableContent(out fragment, index.ToString(), bold);
            cellst.Paragraphs.Add(fragment);
            initTableContent(out fragment, titlu, bold);
            fragment.HorizontalAlignment = HorizontalAlignment.Left;
            cellmij.Paragraphs.Add(fragment);
            initTableContent(out fragment, string.Format("{0:0.00}", value), bold);
            celldr.Paragraphs.Add(fragment);
        }
    }

    public static partial class ExportPdf
    {
        public static void ToImgs(this DevizData devizData, string path, string filename="img", int entersPM = 1, int entersMT = 1)
        {
            if (!Directory.Exists("tmps")) Directory.CreateDirectory("tmps");
            if (!path.EndsWith('\\')) path += '\\';

            devizData.ToPDF("tmps\\tmppdf.pdf", entersPM, entersMT);
            Document doc = new Document("tmps\\tmppdf.pdf");
            Resolution resolution = new Resolution(DevizSetari.ImgResolution);
            PngDevice imgDevice = new PngDevice(resolution);
            ConvertPDFtoImage(imgDevice, doc, "tmps\\tmpimg");
            var imgs = new DirectoryInfo(Application.StartupPath + "tmps").GetFiles()
                .Where(f => f.Extension == ".png")
                .Where(f => f.FullName.Contains("tmpimg")); 
            MessageBox.Show($"{imgs.Count()}");
            int i = 1;
            foreach(var img in imgs)
            {
                CleanWatermark(img.FullName, $"{path}{filename}{i++}.png", DevizSetari.ImgClearUp);
            }
            Directory.Delete("tmps", true);
        }

        static void ConvertPDFtoImage(ImageDevice imageDevice, Document pdfDocument, string fname="img")
        {
            for (int pageCount = 1; pageCount <= pdfDocument.Pages.Count; pageCount++)
            {
                using (FileStream imageStream = new FileStream($"{fname}{pageCount}.png", FileMode.Create))
                {
                    // Convert a particular page and save the image to stream
                    imageDevice.Process(pdfDocument.Pages[pageCount], imageStream);
                    // Close stream
                    imageStream.Close();
                }
            }
        }

        static void CleanWatermark(string pathin, string pathout, int height = 120)
        {
            Bitmap bitmap = new Bitmap(pathin);
            using(Graphics g = Graphics.FromImage(bitmap))
            {
                g.FillRectangle(Brushes.White, 0, 0, bitmap.Width, height);
                bitmap.Save(pathout);
            }
            bitmap.Dispose();
        }
    }
}
