import React, {useContext, useState} from "react";
import {AuthContext} from "../components/util/AuthProvider";
import {User} from "../model/user";
import {Alert, Button} from "react-bootstrap";
import {userService} from "../service/users";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPencil, faSave, faX} from "@fortawesome/free-solid-svg-icons";

interface FormErrors {
  username?: string;
  email?: string;
  password?: string;
  confirmPassword?: string;
}

export default function Profile() {
  const authState = useContext(AuthContext);
  const [user, setUser] = useState<User>(authState.account!);
  const [editing, setEditing] = useState(false);
  const [password, setPassword] = useState(authState.account?.password);
  const [errors, setErrors] = useState({} as FormErrors);

  const onChangeUsername = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUser({...user, username: e.target.value});
  }

  const onChangeEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUser({...user, email: e.target.value});
  }

  const onChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  }

  const onChangeConfirmPassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUser({...user, password: e.target.value});
  }

  const validate = () => {
    const localErrors: FormErrors = {};
    if(user.username.length == 0) {
      localErrors.username = "Username is required.";
    }
    if(user.email.length == 0) {
      localErrors.email = "Email is required.";
    }
    if(password?.length == 0) {
      localErrors.password = "Password is required.";
    }
    if(user.password.length == 0) {
      localErrors.confirmPassword = "Confirm Password is required.";
    }
    if(user.password != password) {
      localErrors.confirmPassword = "Passwords do not match.";
    }

    if(Object.keys(localErrors).length > 0) {
      console.log(localErrors)
      setErrors(localErrors);
      return false;
    }
    return true;
  }

  const saveUser = async () => {
    if(!validate()) {
      return;
    }
    try {
      setEditing(false);
      await userService.put(user.id, user, authState.account?.token);
      // update the token in the header
      const resp = await userService.login(user.username, password!);
      authState.setAccount({...user, token: resp.token});
    } catch (err) {
      console.log(err);
      alert(err);
    }
  }

  const toggleEdit = () => {
    setEditing(!editing);
    if(editing) {
      // reset the form
      setUser(authState.account!);
      setPassword(authState.account?.password);
    }
  }

  return (
    <>
      {Object.keys(errors).length > 0 &&
          <Alert variant="danger" dismissible>
              Please fill in all required fields before submitting the form.
              <ul>
                {Object.keys(errors).map(key => <li>{errors[key]}</li>)}
              </ul>
          </Alert>}
      <h1 className="text-center">Profile</h1>
      <div className="row">
        <div className="col-md-6 offset-md-3">
          <form onSubmit={saveUser}>
            <div className="form-group mt-2">
              <label htmlFor="username">Username</label>
              <input type="text" className="form-control" readOnly={!editing}
                     value={user.username} onChange={onChangeUsername}/>
            </div>
            <div className="form-group mt-2">
              <label htmlFor="email">Email</label>
              <input type="text" className="form-control" readOnly={!editing}
                     value={user.email} onChange={onChangeEmail}/>
            </div>
            {editing &&
              <>
                <div className="form-group mt-2">
                    <label htmlFor="password">Password</label>
                    <input type="password" className="form-control" readOnly={!editing}
                           value={password} onChange={onChangePassword}/>
                </div>
                <div className="form-group">
                  <label htmlFor="password">Confirm Password</label>
                  <input type="password" className="form-control" readOnly={!editing}
                         value={user.password} onChange={onChangeConfirmPassword}/>
                </div>
              </>
            }
          </form>
          <div className="d-flex justify-content-around mt-2">
            <Button variant="primary" onClick={toggleEdit}>
              {editing ?
                <> <FontAwesomeIcon icon={faX}/> Cancel </> :
                <> <FontAwesomeIcon icon={faPencil}/> Edit </>
              }
            </Button>
            {editing &&
                <Button variant="success" onClick={saveUser}>
                    <FontAwesomeIcon icon={faSave}/> Save
                </Button>
            }
          </div>
        </div>
      </div>
    </>
  );
}