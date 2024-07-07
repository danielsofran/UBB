import {Button, Col, Form, InputGroup} from "react-bootstrap";
import {Link, useNavigate} from "react-router-dom";
import {useContext, useState} from "react";
import {AxiosRequestConfig, AxiosResponse, isAxiosError} from "axios";
import {AuthContext} from "../components/util/AuthProvider.tsx";
import {User} from "../model/user.ts";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEye, faEyeSlash} from "@fortawesome/free-regular-svg-icons";
import {axiosInstance} from "../api/axiosInstance.ts";

export default function Login()
{
    const [username, setUsername] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [isInvalid, setIsInvalid] = useState<boolean>(false);
    const [isPasswordVisible, setIsPasswordVisible] = useState<boolean>(false);
    const authState = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogin = async () => {
        const userData = {
            username,
            password,
            email: ""
        }

        let response: AxiosResponse;
        try {
            response = await axiosInstance.post('/users/login', userData);
        } catch (error) {
            if (isAxiosError(error)) {
                console.error(`Error: got response code ${error.code} at POST /login`);
                setIsInvalid(true);
                return;
            }

            console.error("Error at POST /login");
            throw error;
        }
        setIsInvalid(false);

        // Either the server could return all the user's data,
        // or we could make another request for it.
        const userId = response.data.id;
        const token = response.data.token;

        try {
            const axiosConfig: AxiosRequestConfig = {
              headers: {
                  Authorization: `Bearer ${token}`
              }
            };
            response = await axiosInstance.get(`/users/${userId}`, axiosConfig);
        } catch (error) {
            if (isAxiosError(error)) {
                console.error(`Error: got response code ${error.code} at GET /users/${userId}`);
                setIsInvalid(true);
                return;
            }

            console.error(`Error at GET /users/${userId}`);
            throw error;
        }

        const accountData: User = {...response.data, token};
        authState.setAccount(accountData);
        console.log("Login successful", userData);

        navigate("/home");
    };

    // @ts-expect-error: The type of the event is a somewhat complex HTML element,
    // and I believe that we don't gain anything by specifying it.
    const resetValidityWrapper = (event, callable) => {
        setIsInvalid(false);
        callable(event.target.value);
    }

    return (
        <Col className="d-flex justify-content-center">
            <Form className="mt-4 col-md-4 d-flex align-items-stretch flex-column gap-4">
                <h1 className="text-center">CauseConnect</h1>

                <Form.Group controlId="formUsername">
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text"
                                  placeholder="Enter your username"
                                  isInvalid={isInvalid}
                                  value={username}
                                  onChange={e => resetValidityWrapper(e, setUsername)}/>
                </Form.Group>

                <Form.Group controlId="formPassword">
                    <Form.Label>Password</Form.Label>
                    <span className="d-flex align-items-center gap-2">
                        <InputGroup hasValidation>
                            <Form.Control
                                type={isPasswordVisible ? "text" : "password"}
                                value={password}
                                isInvalid={isInvalid}
                                onChange={e => resetValidityWrapper(e, setPassword)}
                                placeholder="Password"
                            />
                            <InputGroup.Text>
                                <FontAwesomeIcon
                                    title="Toggle password visibility" icon={isPasswordVisible? faEyeSlash : faEye}
                                    onClick={() => setIsPasswordVisible(!isPasswordVisible)}
                                />
                            </InputGroup.Text>
                            <Form.Control.Feedback type="invalid">
                                The username or the password is not correct
                            </Form.Control.Feedback>
                        </InputGroup>
                    </span>
                </Form.Group>

                <Button variant="primary" type="button" onClick={handleLogin}>Log in</Button>

                <div className="mt-2 d-flex flex-column text-center">
                    <em className="text-secondary fst-normal">Don't have an account?</em>
                    <Link className="text-primary" to="/register">Create one instead</Link>
                </div>
            </Form>
        </Col>
    );
}
