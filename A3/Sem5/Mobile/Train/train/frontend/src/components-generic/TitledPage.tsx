import {IonContent, IonHeader, IonPage, IonTitle, IonToolbar} from "@ionic/react";
import ExploreContainer from "../components/ExploreContainer";
import React from "react";

interface TitledPageProps {
  title: string;
  children: React.ReactNode;
}

export const TitledPage: React.FC<TitledPageProps> = (props: TitledPageProps) => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>{props.title}</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">{props.title}</IonTitle>
          </IonToolbar>
        </IonHeader>
        {props.children}
      </IonContent>
    </IonPage>
  );
}