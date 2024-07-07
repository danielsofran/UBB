using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using System.Threading.Tasks;
using MillStrategy.Structure;

namespace MillStrategy.Domain
{
    // PROPS
    public partial class Tabla : ITablaProps
    {
        IPlayer player = new Player();
        Pozitii pozitiivalide = new Pozitii();
        Dictionary<PiesaColor, IPlayer> playerbycolor = null;
        Structuri morivalide, morilibere, moriocupate;
        Pozitie mutareStart;

        public Tabla()
        {
            pozitiivalide = Generator.Pozitii;
            morivalide = Generator.MoriValide;
            Reset(false);
        }

        public bool SePunPiese { get => Player1.State == PlayerState.Pune || Player2.State == PlayerState.Pune; }
        public bool IsGameOver { get; set; } = false;
        public PiesaColor Winner { get; set; } = PiesaColor.None;
        public IPozitie PozitieMutareStart { get => mutareStart; set => mutareStart = (Pozitie)value; }

        public IPlayer Player1 { get; set; }
        public IPlayer Player2 { get; set; }
        public IPlayer Player { get => player; 
            set
            {
                player = value;
                OnPlayerChanged(new EventArgs());
            }
        }
        public IPlayer OtherPlayer
        {
            get
            {
                if (Player == Player1) return Player2;
                if (Player == Player2) return Player1;
                return null;
            }
        }
        public Dictionary<PiesaColor, IPlayer> PlayerByColor
        {
            get
            {
                if(playerbycolor != null) return playerbycolor;
                playerbycolor = new Dictionary<PiesaColor, IPlayer>();
                playerbycolor[Player1.PiesaColor] = Player1;
                playerbycolor[Player2.PiesaColor] = Player2;
                return playerbycolor;
            }
        }

        public IPozitii PozitiiValide { get => pozitiivalide; }
        public IStructuri MoriValide { get => morivalide; }
        public IStructuri MoriLibere { get => morilibere; }
        public IStructuri MoriOcupate { get => moriocupate; }
        public Dictionary<IPozitie, IPiesa> Piese { get; set; }
        public Dictionary<PiesaColor, IPozitii> PiesePeCulori { 
            get
            {
                Dictionary<PiesaColor, IPozitii> rez = new Dictionary<PiesaColor, IPozitii>();
                rez[PiesaColor.White] = new Pozitii();
                rez[PiesaColor.Black] = new Pozitii();
                foreach(KeyValuePair<IPozitie, IPiesa> keyValue in Piese)
                    if(keyValue.Value.Ocupata)
                        rez[keyValue.Value.PlayerColor].Add(keyValue.Key);
                return rez;
            }
        }

        public IPozitie GetPozitie(IPozitie poz) => Piese.Keys.Where((IPozitie poz0) => poz.Cod == poz0.Cod).First();
    }
    
    // EVENTS
    public partial class Tabla : ITablaEvents
    {
        public event EventHandler PlayerChanged;
        public event EventHandler GameOver;
        public event EventHandler GameRestarted;

        protected virtual void OnPlayerChanged(EventArgs e = null)
        {
            PlayerChanged?.Invoke(this, e);
        }
        protected virtual void OnGameOver(EventArgs e = null)
        {
            GameOver?.Invoke(this, e);
        }
        protected virtual void OnGameReseted(EventArgs e = null)
        {
            GameRestarted?.Invoke(this, e);
        }

        public event EventHandler<PozitieEventArgs> PuneBefore;
        public event EventHandler<PozitieEventArgs> PuneAfter;
        public event EventHandler<PozitieEventArgs> IaBefore;
        public event EventHandler<PozitieEventArgs> IaAfter;
        public event EventHandler<PozitieEventArgs> MutaStartBefore;
        public event EventHandler<PozitieEventArgs> MutaStartAfter;
        public event EventHandler<PozitieEventArgs> MutaStopBefore;
        public event EventHandler<PozitieEventArgs> MutaStopAfter;
                       
        protected virtual void OnPuneBefore(PozitieEventArgs e)
        {
            PuneBefore?.Invoke(this, e);
        }
        protected virtual void OnPuneAfter(PozitieEventArgs e)
        {
            PuneAfter?.Invoke(this, e);
        }
        protected virtual void OnIaBefore(PozitieEventArgs e)
        {
            IaBefore?.Invoke(this, e);
        }
        protected virtual void OnIaAfter(PozitieEventArgs e)
        {
            Pozitie poz = (Pozitie)e.Pozitie;
            for (int i = 0; i < MoriOcupate.Count; i++)
                if (MoriOcupate[i].ContainsPozitie(poz))
                {
                    MoriOcupate.RemoveAt(i);
                    i--;
                }
            IaAfter?.Invoke(this, e);
        }
        protected virtual void OnMutaStartBefore(PozitieEventArgs e)
        {
            MutaStartBefore?.Invoke(this, e);
        }
        protected virtual void OnMutaStartAfter(PozitieEventArgs e)
        {
            MutaStartAfter?.Invoke(this, e);
        }
        protected virtual void OnMutaStopBefore(PozitieEventArgs e)
        {
            IPozitie poz = e.Pozitie;
            foreach (var moara in MoriOcupate)
                if (moara.ContainsPozitie(poz))
                {
                    MoriLibere.Add(moara);
                    MoriOcupate.Remove(moara);
                    break;
                }
            MutaStopBefore?.Invoke(this, e);
        }
        protected virtual void OnMutaStopAfter(PozitieEventArgs e)
        {
            IPozitii moara = CheckMills();
            if (moara != null) // verific daca am moara
            {
                Player.State = PlayerState.Ia;
                MoriOcupate.Add(moara);
                MoriLibere.Remove(moara);
                //for (int i = 0; i < MoriLibere.Count; i++)
                //    if (MoriLibere[i].ToString() == moara.ToString())
                //        MoriLibere.RemoveAt(i);
            }
            else
            {
                ChangePlayer();
            }
            MutaStopAfter?.Invoke(this, e);
        }

        public event EventHandler<PozitiiEventArgs> MillDetected;
        protected virtual void OnMillDetected(PozitiiEventArgs e)
        {
            MillDetected?.Invoke(this, e);
        }
    }
    
    // GAMEPLAY
    public partial class Tabla : ITablaGameplay
    {
        public void ChangePlayer()
        {
            if (Player == Player1) Player = Player2;
            else if (Player == Player2) Player = Player1;
        }

        public IPozitii PieseNotInMill(PiesaColor color)
        {
            Pozitii rez = new Pozitii();
            foreach (var pozitie in PiesePeCulori[color])
                if (!MoriOcupate.ContainsPozitie(pozitie))
                    rez.Add(pozitie);
            return rez;
        }

        public IPozitii CheckMills()
        {
            foreach (var moara_veche in MoriLibere)
            {
                Pozitii moara = new Pozitii();
                foreach (var poz in moara_veche)
                    moara.Add(Piese.Keys.Where((IPozitie p) => p.Cod == poz.Cod).First());
                var color = Piese[moara[0]].PlayerColor;
                bool diferit = false;
                if (color == PiesaColor.None) continue;
                foreach (var poz in moara)
                    if (Piese[poz].PlayerColor != color)
                    {
                        diferit = true;
                        break;
                    }
                if (!diferit)
                {
                    OnMillDetected(new PozitiiEventArgs() { Pozitii = moara });
                    return moara;
                }
            }
            return null;
        }

        public void Pune(IPozitie poz)
        {
            if (Piese[poz].Libera)
            {
                OnPuneBefore(new PozitieEventArgs() { Pozitie = poz });
                Piese[poz] = Player.Pune();
                IPozitii moara = CheckMills();
                if (moara != null) // verific daca am moara
                {
                    Player.State = PlayerState.Ia;
                    MoriOcupate.Add(moara);
                    for(int i=0;i<MoriLibere.Count;i++)
                        if (MoriLibere[i].ToString() == moara.ToString())
                            MoriLibere.RemoveAt(i);
                    OnPuneAfter(new PozitieEventArgs() { Pozitie = poz });
                }
                else
                {
                    if (Player.DePus.Count == 0) Player.State = PlayerState.MutaStart;
                    OnPuneAfter(new PozitieEventArgs() { Pozitie = poz });
                    ChangePlayer();
                }
            }
            else throw new PozitieException("Pozitie ocupata!");
        }

        public void Ia(IPozitie poz)
        {
            if (Piese[poz].PlayerColor == OtherPlayer.PiesaColor) // piesa de culoare opusa
            {
                IPozitii notinmill = PieseNotInMill(OtherPlayer.PiesaColor);
                if (notinmill.Count > 0 && !notinmill.ContainsPozitie(poz))
                    throw new PozitieException("Nu puteti lua din moara!");
                // Iau piesa
                OnIaBefore(new PozitieEventArgs() { Pozitie = poz });
                Player.Ia(Piese[poz]);
                Piese[poz] = new Piesa();
                if (Player.DePus.Count > 0) Player.State = PlayerState.Pune;
                else Player.State = PlayerState.MutaStart;
                OnIaAfter(new PozitieEventArgs() { Pozitie = poz });
                ChangePlayer();
            }
            else throw new PozitieException("Alegeti o piesa de culoare opusa!");
        }

        public void MutaStart(IPozitie poz)
        {
            if (Piese[poz].PlayerColor == Player.PiesaColor)
            {
                OnMutaStartBefore(new PozitieEventArgs() { Pozitie = poz });
                PozitieMutareStart = poz;
                Player.State = PlayerState.MutaStop;
                OnMutaStartAfter(new PozitieEventArgs() { Pozitie = poz });
                // DO NOT CHANGE PLAYER
            }
        }

        private void Muta(IPozitie start, IPozitie stop)
        {
            var aux = Piese[start];
            Piese[start] = Piese[stop];
            Piese[stop] = aux;
        }

        private PiesaColor IsLoss()
        {
            if (PiesePeCulori[PiesaColor.Black].Count < 3)
                if (PlayerByColor[PiesaColor.Black].DePus.Count == 0)
                    return PiesaColor.Black;
            if (PiesePeCulori[PiesaColor.White].Count < 3)
                if (PlayerByColor[PiesaColor.White].DePus.Count == 0)
                    return PiesaColor.White;
            return PiesaColor.None;
        }

        public void MutaStop(IPozitie poz)
        {
            if (Piese[poz].Libera)
            {
                if (PiesePeCulori[Player.PiesaColor].Count > 3)
                {
                    if (poz.Vecini.Any((OctalNumber nr) => nr == PozitieMutareStart.Cod))
                    {
                        OnMutaStopBefore(new PozitieEventArgs() { Pozitie = PozitieMutareStart });
                        Muta(PozitieMutareStart, poz);
                        OnMutaStopAfter(new PozitieEventArgs() { Pozitie = poz });
                    }
                }
                else
                {
                    OnMutaStopBefore(new PozitieEventArgs() { Pozitie = poz });
                    Muta(PozitieMutareStart, poz);
                    OnMutaStopAfter(new PozitieEventArgs() { Pozitie = poz });
                }
            }
        }

        public void ProcessPosition(IPozitie pozitie)
        {
            if (!IsGameOver)
            {
                IPozitie poz = Piese.Keys.Where((IPozitie p) => p.Cod == pozitie.Cod).First();
                if (Player.State == PlayerState.Pune) Pune(poz);
                else {
                    if (Player.State == PlayerState.Ia) Ia(poz);
                    else if (Player.State == PlayerState.MutaStart) MutaStart(poz);
                    else if (Player.State == PlayerState.MutaStop) MutaStop(poz);
                    else throw new MyException("Invalid Player State!");
                    if(IsLoss() != PiesaColor.None)
                    {
                        IsGameOver = true;
                        OnGameOver();
                    }
                }
            }
        }
    
        public void Reset(bool callOnReset = true)
        {
            IsGameOver = false;
            Winner = PiesaColor.None;
            Player1 = new Player(PiesaColor.White);
            Player2 = new Player(PiesaColor.Black);
            Player = Player1;
            morilibere = Generator.MoriValide;
            moriocupate = new Structuri();
            Piese = new Dictionary<IPozitie, IPiesa>();
            foreach (Pozitie poz in pozitiivalide)
                Piese[poz] = new Piesa();
            if(callOnReset) OnGameReseted();
        }
    }
}
