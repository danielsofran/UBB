import React, {useEffect, useRef, useState} from 'react';
import logo from './logo.svg';
import './App.css';
import {ArtistRepository} from "./api/repo/artist";
import {Artist} from "./models/artist";
import {ArtistComp} from "./components/Artist";
import {ArtistAddForm} from "./components/ArtistAddForm";
import {ArtistUpdateForm} from "./components/ArtistUpdateForm";

function App() {
  const artistRepository = new ArtistRepository();

  const [artists, setArtists] = useState<Artist[]>([]);
  const [showAddForm, setShowAddForm] = useState<boolean>(false);
  const [showUpdateForm, setShowUpdateForm] = useState<boolean>(false);
  const [artistUpdated, setArtistUpdated] = useState<Artist>(new Artist());
  const [btnText, setBtnText] = useState<string>('Adauga artist');

  useEffect(() => {
    artistRepository.getAll().then((response) => {
      setArtists(response);
      console.warn(response)
    });
  }, []);

  const addArtist = () => {
    if (showAddForm) {
        setShowAddForm(!showAddForm)
        setBtnText('Adauga artist');
    } else {
        setShowAddForm(!showAddForm)
        setBtnText('❌ Ascunde formular');
    }
  }

  const onUpdate = (artist: Artist) => {
      setArtistUpdated(artist);
      setShowUpdateForm(!showUpdateForm);
  }


  return (
    <div className="App">
        <header>Bine ati venit!</header>
        <section className="subtitle">
            <p>Va prezentam artistii nostri:</p>
            <button onClick={addArtist}>{btnText}</button>
        </section>
        <main>
            {showAddForm && <ArtistAddForm/>}
            {showUpdateForm && <ArtistUpdateForm artist={artistUpdated}/>}
            <div className="container">{artists.map((artist) => <ArtistComp key={artist.id} artist={artist} onUpdate={onUpdate}/>)}</div>
        </main>
    </div>
  );
}

export default App;
