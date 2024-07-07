import {IonItem, IonLabel, IonList, IonNote} from "@ionic/react";
import React from "react";

interface DataListProps {
  data: any[];
  render?: (item: any) => React.ReactNode;
}

export const DataList: React.FC<DataListProps> = (props: DataListProps) => {
  return (
    <IonList>
      {props.data.map((item, index) => (
        <IonItem key={index}>
          {props.render ? props.render(item) :
            null
          }
        </IonItem>
      ))
      }
    </IonList>
  );
}