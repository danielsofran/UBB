import { Redirect, Route } from 'react-router-dom';
import {IonApp, IonIcon, IonLabel, IonRouterOutlet, IonTabBar, IonTabs, IonTabButton, setupIonicReact} from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';
import Photos from './pages/camera/Photos';

/* Core CSS required for Ionic components to work properly */
import '@ionic/react/css/core.css';

/* Basic CSS for apps built with Ionic */
import '@ionic/react/css/normalize.css';
import '@ionic/react/css/structure.css';
import '@ionic/react/css/typography.css';

import '@ionic/react/css/padding.css';
import '@ionic/react/css/float-elements.css';
import '@ionic/react/css/text-alignment.css';
import '@ionic/react/css/text-transformation.css';
import '@ionic/react/css/flex-utils.css';
import '@ionic/react/css/display.css';

/* Theme variables */
import './theme/variables.css';
import {cameraOutline, navigateOutline, starHalfOutline} from "ionicons/icons";
// import Location from "./pages/locations/Location";

setupIonicReact();

const App: React.FC = () => (
    <IonApp>
      <IonReactRouter>
        <IonTabs>
          <IonRouterOutlet>
            <Route path="/photos" component={Photos} exact={true} />
            {/*<Route path="/maps" component={Location} exact={true} />*/}
            <Route exact path="/" render={() => <Redirect to="/photos" />} />
          </IonRouterOutlet>
          <IonTabBar slot="bottom">
            <IonTabButton tab="camera" href="/photos">
              <IonIcon icon={cameraOutline} />
              <IonLabel>Photos Gallery</IonLabel>
            </IonTabButton>
            {/*<IonTabButton tab="locations" href="/maps">*/}
            {/*  <IonIcon icon={navigateOutline} />*/}
            {/*  <IonLabel>Locations</IonLabel>*/}
            {/*</IonTabButton>*/}
          </IonTabBar>
        </IonTabs>
      </IonReactRouter>
    </IonApp>
);

export default App;
