import {useState} from "react";
import {ArtistRepository} from "../api/repo/artist";
import {Artist} from "../models/artist";
import '../App.css';

export const ArtistAddForm = () => {
    const artistRepository = new ArtistRepository();
    const [name, setName] = useState<string>('');

    const handleSubmit = (event: any) => {
        event.preventDefault();
        console.log(name);
        let artist: Artist = new Artist(); artist.name = name;
        artistRepository.create(artist)
            .then(() => window.location.reload())
            .catch((error) => console.log(error));
    }

    return (
        <form onSubmit={handleSubmit}>
            <div className="formular formular-add">
                <header>Adauga artist</header>
                <main>
                    <div className="row">
                        <label>Name:</label>
                        <input type="text" value={name} onChange={(event) => setName(event.target.value)}/>
                    </div>
                </main>
                <input type="submit" value="Adauga"/>
            </div>
        </form>
    );
}