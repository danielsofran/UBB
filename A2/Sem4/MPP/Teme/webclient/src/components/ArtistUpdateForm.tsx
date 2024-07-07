import {useEffect, useState} from "react";
import {ArtistRepository} from "../api/repo/artist";
import {Artist} from "../models/artist";
import '../App.css';

export const ArtistUpdateForm = ({artist} : {artist: Artist}) => {
    const artistRepository = new ArtistRepository();
    const [name, setName] = useState<string>(artist.name);

    useEffect(() => {
        console.log(artist);
    },[]);

    const handleSubmit = (event: any) => {
        event.preventDefault();
        artist.name = name;
        artistRepository.update(artist)
            .then(() => window.location.reload())
            .catch((error) => console.log(error));
    }

    return (
        <form onSubmit={handleSubmit}>
            <div className="formular formular-update">
                <header>Actualizeaza artist</header>
                <main>
                    <div className="row">
                        <label>Name:</label>
                        <input type="text" value={name} onChange={(event) => setName(event.target.value)}/>
                    </div>
                </main>
                <input type="submit" value="Actualizeaza"/>
            </div>
        </form>
    );
}