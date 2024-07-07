using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;
using System.Collections;

namespace MillStrategy.Structure
{
    public enum PiesaColor { None, White, Black };
    public enum PiesaState { Nepusa, Pusa, Luata };
    public enum PlayerState { Pune, Ia, MutaStart, MutaStop };

    public interface IPozitie : IComparable, IEquatable<IPozitie>
    {
        OctalNumber Cod { get; }
        List<OctalNumber> Vecini { get; }
        int Square { get; }
        bool IsDiagonal { get; }
        bool IsLine { get; }
        string ToString();
        IPozitie FromString(string pozitie);
    }

    public abstract class IPozitii : List<IPozitie>, IEquatable<IPozitii>
    {
        public abstract override string ToString();
        public abstract IPozitii FromString(string pozitii);

        public abstract bool ContainsPozitie(IPozitie pozitie);
        public abstract bool Equals(IPozitii other);
    }

    public abstract class IStructuri : List<IPozitii>, IEquatable<IStructuri>
    {
        public abstract override string ToString();
        public abstract IStructuri FromString(string txt);

        public abstract bool ContainsPozitii(IPozitii pozitii);
        public abstract bool ContainsPozitiiAnyOrder(IPozitii pozitii);
        public abstract bool ContainsPozitie(IPozitie pozitie);
        public abstract bool Equals(IStructuri other);
    }

    public interface IPiesa
    {
        bool Ocupata { get; }
        bool Libera { get; set; }
        bool IsBlack { get; set; }
        bool IsWhite { get; set; }
        bool Is(PiesaColor color);
        PiesaColor PlayerColor { get; set; }
    }

    public interface IPlayer
    {
        string Nume { get; set; }
        PiesaColor PiesaColor { get; set; }
        PlayerState State { get; set; }
        List<IPiesa> DePus { get; set; }
        List<IPiesa> Luate { get; set; }

        void Reset(); // reseteaza piesele
        IPiesa Pune();
        void Ia(IPiesa piesa);
    }

    public interface ITablaEvents
    {
        event EventHandler PlayerChanged;
        event EventHandler GameOver;
        event EventHandler GameRestarted;
        event EventHandler<PozitieEventArgs> PuneBefore;
        event EventHandler<PozitieEventArgs> PuneAfter;
        event EventHandler<PozitieEventArgs> IaBefore;
        event EventHandler<PozitieEventArgs> IaAfter;
        event EventHandler<PozitieEventArgs> MutaStartBefore;
        event EventHandler<PozitieEventArgs> MutaStartAfter;
        event EventHandler<PozitieEventArgs> MutaStopBefore;
        event EventHandler<PozitieEventArgs> MutaStopAfter;
        event EventHandler<PozitiiEventArgs> MillDetected;
    }

    public partial interface ITablaProps {
        bool SePunPiese { get; } // daca inca se pun piese sau se muta
        bool IsGameOver { get; set; }
        PiesaColor Winner { get; set; }
        IPozitie PozitieMutareStart { get; set; }
        IPlayer Player1 { get; set; }
        IPlayer Player2 { get; set; }
        IPlayer Player { get; set; }
        IPlayer OtherPlayer { get; }
        Dictionary<PiesaColor, IPlayer> PlayerByColor { get; }

        IPozitii PozitiiValide { get; } // toate pozitiile disponibile
        IStructuri MoriValide { get; }
        IStructuri MoriLibere { get; }
        IStructuri MoriOcupate { get; }
        Dictionary<IPozitie, IPiesa> Piese { get; set; }
        Dictionary<PiesaColor, IPozitii> PiesePeCulori { get; }
    }

    public interface ITablaGameplay { 
        IPozitii PieseNotInMill(PiesaColor color);
        void ChangePlayer();
        IPozitii CheckMills();

        void Pune(IPozitie poz);
        void Ia(IPozitie poz);
        void MutaStart(IPozitie poz);
        void MutaStop(IPozitie poz);

        void ProcessPosition(IPozitie pozitie);
        void Reset(bool callOnReset);
    }

    public interface ITabla : ITablaEvents, ITablaProps, ITablaGameplay { }
}
