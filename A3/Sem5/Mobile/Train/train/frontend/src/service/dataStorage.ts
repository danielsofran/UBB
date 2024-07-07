import {Storage} from "@ionic/storage";

export class OfflineData {
  data: any;
  state: "read" | "created" | "updated" | "deleted";

  constructor(data: any, state: "read" | "created" | "updated" | "deleted" = "read") {
    this.data = data;
    this.state = state;
  }
}

const dataStorage = new Storage({
  name: 'data',
})

dataStorage.create();

export const getDataFromStorage = async (): Promise<any[]> => {
  const data = []
  await dataStorage.forEach((value, key, index) => {
    if(value.state !== "deleted")
      data.push(value.data)
  })
  return data;
}

export const getDataFromStorageById = async (id: number|string): Promise<OfflineData> => {
  return await dataStorage.get(`${id}`).then(data => {
    if(data.state !== "deleted")
      return data.data;
    else
      return null;
  })
}

export const addDataToStorage = async (data: any) => {
  let randomId = Math.floor(Math.random() * 1000000000000000000).toString();
  // check id not to be already in use
  while(await dataStorage.get(randomId)) {
    randomId = Math.floor(Math.random() * 1000000000000000000).toString();
  }
  await dataStorage.set(`${randomId}`, new OfflineData(data, "created"));
  return randomId;
}

export const updateDataInStorage = async (id: number|string, data: any) => {
  await dataStorage.set(`${id}`, new OfflineData(data, "updated"));
}

export const deleteDataFromStorage = async (id: number|string) => {
  await dataStorage.set(`${id}`, new OfflineData(null, "deleted"));
}

export const clearDataFromStorage = async () => {
  await dataStorage.clear();
}

export const resetStorage = async (data: any[], idFieldName = "code") => {
  await dataStorage.clear();
  data.forEach(item => {
    dataStorage.set(item[idFieldName], new OfflineData(item));
  })
}