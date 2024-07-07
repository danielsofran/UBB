import logo from './logo.svg';
import './App.css'
import './bg/Background.css'
import './menu/Menu.css'
import {Background} from "./bg/Background";
import {Menu} from "./menu/Menu";
import {MenuItem} from "./menu/MenuItem";
import {MenuCenter} from "./menu/MenuCenter";
import {LeopardImage} from "./LeopardImage";

function TestBg() {
  return (
    <div className="App">
      <Background>
        <Menu>
            <MenuCenter><LeopardImage/></MenuCenter>
            <MenuItem><LeopardImage/></MenuItem>
            <MenuItem><LeopardImage/></MenuItem>
            <MenuItem><LeopardImage/></MenuItem>
            <MenuItem><LeopardImage/></MenuItem>
            <MenuItem><LeopardImage/></MenuItem>
            <MenuItem><LeopardImage/></MenuItem>
            <MenuItem><LeopardImage/></MenuItem>
            <MenuItem><LeopardImage/></MenuItem>
        </Menu>
      </Background>
    </div>
  );
}

function App() {
  return (
      <div className="App">
          <Menu>
              <MenuCenter><LeopardImage/></MenuCenter>
              <MenuItem><LeopardImage/></MenuItem>
              <MenuItem><LeopardImage/></MenuItem>
              <MenuItem><LeopardImage/></MenuItem>
              <MenuItem><LeopardImage/></MenuItem>
              <MenuItem><LeopardImage/></MenuItem>
              <MenuItem><LeopardImage/></MenuItem>
              <MenuItem><LeopardImage/></MenuItem>
              <MenuItem><LeopardImage/></MenuItem>
          </Menu>
      </div>
  );
}

export default App;
