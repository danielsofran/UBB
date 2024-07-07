import {Artist} from "../models/artist";
import '../App.css';
import {ArtistRepository} from "../api/repo/artist";

export const ArtistComp = (props: { artist: Artist; onUpdate: (artist: Artist) => void}) => {
    const artistRepository = new ArtistRepository();
    const artist: Artist = props.artist;
    const onUpdate = props.onUpdate;

    function deleteArtist() {
        artistRepository.delete(artist.id)
            .then(() => window.location.reload())
            .catch((error) => alert(error));
    }

    return (
        <div className="artist">
            <header> Artist </header>
            <p>{artist.name}</p>
            <footer>
                <button className="btnEdit" onClick={() => onUpdate(artist)}>Editeaza</button>
                <button className="btnDelete" onClick={deleteArtist}>Sterge</button>
            </footer>
        </div>
    );
}