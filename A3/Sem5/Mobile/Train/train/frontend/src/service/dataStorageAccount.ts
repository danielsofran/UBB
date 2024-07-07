import {Storage} from "@ionic/storage";

const dataStorage = new Storage({
  name: 'account',
})

dataStorage.create();

export const getAccountFromStorage = async (): Promise<any> => {
  return await dataStorage.get(`account`).then(data => {
    return data;
  })
}

export const setAccountToStorage = async (data: any) => {
  await dataStorage.set(`account`, data);
}

export const clearAccountFromStorage = async () => {
  await dataStorage.clear();
}