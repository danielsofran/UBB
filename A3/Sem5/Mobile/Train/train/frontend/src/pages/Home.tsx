import './Home.css';
import {TitledPage} from "../components-generic/TitledPage";
import React, {useEffect, useRef, useState} from "react";
import {useAppDispatch, useAppSelector} from "../store";
import {dataSelector, setData, updateItem} from "../reducers/dataReducer";
import {DataList} from "../components-generic/DataList";
import {customSocket} from "../utils/socket";
import {IonButton, IonInput, IonLabel, IonSelect, IonSelectOption, IonText} from "@ionic/react";
import {getDataFromStorage} from "../service/dataStorage";
import {createOrder, MenuItem} from "../service/service";

const Home: React.FC = () => {
  function useForceUpdate() {
    const [, setValue] = useState(0); // unused state value
    return () => setValue(value => value + 1);
  }
  const forceUpdate = useForceUpdate();
  const dispatch = useAppDispatch();
  const data = useAppSelector(dataSelector);

  const [loading, setLoading] = useState(true);
  const [filter, setFilter] = useState("all");
  const [failedCodes, setFailedCodes] = useState<number[]>([]);
  //const [inProgress, setInProgress] = useState<Record<number, boolean>>({});
  const inProgressRef = useRef({})
  const [areAllItemsInProgress, setAreAllItemsInProgress] = useState(false);

  const [shouldStartRequest, setShouldStartRequest] = useState(false);

  const isItemInProgress = (item: any) => {
    const itemExists = Object.keys(inProgressRef.current).includes(item.code);
    const itemInProgress = inProgressRef.current[item.code];
    console.log("isItemInProgress", item, itemExists, itemInProgress)
    console.log("inProgressRef.current", inProgressRef.current)
    return Object.keys(inProgressRef.current).includes(item.code) &&
      inProgressRef.current[item.code];
  }

  const setItemInProgress = (item: any, value: boolean) => {
    //setInProgress({...inProgressRef.current, [item.code]: value});
    const newInProgress = {...inProgressRef.current};
    newInProgress[item.code] = value;
    inProgressRef.current = newInProgress;
    forceUpdate();
  }

  const setItemsInProgress = (items: any[], value: boolean) => {
    const newInProgress = {...inProgressRef.current};
    items.forEach((item) => {
      newInProgress[item.code] = value;
    })
    inProgressRef.current = newInProgress;
    forceUpdate();
  }

  useEffect(() => {
    console.warn("inProgressRef.current", inProgressRef.current)
  }, [inProgressRef.current])

  const isItemInFilter = (item: any) => {
    if(filter === "all")
      return true;
    else
      return item.quantity && item.quantity !== 0;
  }

  const initQuantity = (data: any[]) => {
    data.forEach((item: any) => {
      if(!item.quantity)
        item.quantity = 0;
    })
  }

  useEffect(() => {
    let socket = null
    getDataFromStorage().then((data) => {
      if(!data || data.length === 0) {
        socket = customSocket((data: any) => {
          initQuantity(data)
          dispatch(setData(data));
          setLoading(false);
          console.log(data);
        }, (error: any) => {
          alert(error)
        })
      }
      else {
        initQuantity(data)
        dispatch(setData(data));
        setLoading(false);
        console.log(data);
      }
    })
    return () => {
      socket?.()
    }
  }, []);

  const submit = () => {
    setItemsInProgress(data, true)
    setShouldStartRequest(true);
    setAreAllItemsInProgress(true)
    forceUpdate();
  }

  useEffect(() => {
    if(!shouldStartRequest)
      return;
    const failedCodes = [];
    const itemPromise = async (item: any) => {
      try{
        setItemInProgress(item, true);
        forceUpdate();
        await createOrder(item)
        setItemInProgress(item, false)
        forceUpdate();
      }
      catch (e) {
        setItemInProgress(item, false)
        forceUpdate();
        failedCodes.push(item.code);
        console.log("Failed to create order for item: ", item, e)
      }
    }
    // wait 5 seconds, then start creating orders
    Promise.all(data.map(async (item: any) => {
      await itemPromise(item);
    })).then(() => {
      setFailedCodes(failedCodes)
      setShouldStartRequest(false);
      setAreAllItemsInProgress(false);
    })
  }, [shouldStartRequest])

  const renderItem = (item: any) => {
    const menuItem = item as MenuItem;

    const [editMode, setEditMode] = useState(false);
    const [quantity, setQuantity] = useState(menuItem.quantity);

    const changeQuantity = (e) => {
      const value = e.detail.value;
      setQuantity(value);
    }

    const onChange = () => {
      setEditMode(false);
      if(editMode) {
        let numberQuantity = 0;
        try{ numberQuantity = Number(quantity) }
        catch (e) { numberQuantity = quantity }
        const newMenuItem = {...menuItem, quantity: numberQuantity};
        dispatch(updateItem(newMenuItem))
      }
    }

    const itemInProgress = isItemInProgress(item);

    return (
      <div style={{display: "flex", gap: 20}}>
        {!isItemInFilter(menuItem) ? null :
          <>
            <IonLabel
              style={{color: failedCodes.includes(menuItem.code) ? "red" : "white"}}
            >{menuItem.name}</IonLabel>
            {editMode ?
              <div style={{display: "flex", gap: 20}}>
              <IonInput value={quantity} onIonChange={changeQuantity} />
              <IonButton onClick={onChange}>Done</IonButton>
              </div>
              :
              <IonLabel onClick={() => setEditMode(true)}>Quantity: {quantity}</IonLabel>
            }
            {itemInProgress ? <IonText><h3>In progress...</h3></IonText> : null}
          </>
          }
      </div>
    );
  }

  return (
    <TitledPage title={"Home"}>
      <IonSelect
        value={filter}
        placeholder="Select One"
        onIonChange={e => setFilter(e.detail.value)}
      >
        <IonSelectOption value="all">All</IonSelectOption>
        <IonSelectOption value="ordered">Ordered</IonSelectOption>
      </IonSelect>

      {loading ? <IonText><h3>Loading...</h3></IonText>
       :
        <DataList data={data} render={renderItem} />
      }

      <IonButton onClick={() => submit()}>Submit</IonButton>
    </TitledPage>
  );
};

export default Home;
