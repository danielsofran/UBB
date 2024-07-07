import {Service} from "./base";
import {User} from "../model/user";
import {axiosInstance} from "../api/axiosInstance";

class UserService extends Service<User> {
  constructor() {
    super('/users');
  }

  async login(identifier: string, password: string): Promise<User> {
    try {
      const response = await axiosInstance.post(`${this.url}/login`, {
        username: identifier,
        password,
        email: "",
      });
      return response.data;
    } catch (e) {
      // now try with email
      try {
        const response = await axiosInstance.post(`${this.url}/login`, {
          username: "",
          password,
          email: identifier,
        });
        return response.data;
      }
      catch (e) {
        console.error(e, `${this.url}/login`);
        throw e;
      }
    }
  }
}

export const userService = new UserService();