import {Button, Col, Form, InputGroup} from "react-bootstrap";
import {Link, useNavigate} from "react-router-dom";
import {useContext, useRef, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEye, faEyeSlash} from "@fortawesome/free-regular-svg-icons";
import {User} from "../model/user";
import {isAxiosError} from "axios";
import {axiosInstance, REDIRECT_PREFIX} from "../api/axiosInstance";
import {AuthContext} from "../components/util/AuthProvider";

export default function Register() {
    const [username, setUsername] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [isPasswordVisible, setIsPasswordVisible] = useState<boolean>(false);

    const navigate = useNavigate();
    const authState = useContext(AuthContext);
    const formRef = useRef<any>();

    const handleRegister = async () => {
        try {
            if (!formRef.current!.checkValidity()) {
                formRef.current!.reportValidity();
                return;
            }

            const userData = {
                email,
                password,
                username,
            }

            const registerResponse = await axiosInstance.post('/users/register', userData);

            if (!registerResponse.data) {
                console.error("Error on POST /users");
                return;
            }
            console.log("Register successful", registerResponse);

            // automatically log in
            const loginResponse = await axiosInstance.post('/users/login', userData);
            const user: User = {
                id: loginResponse.data.id,
                token: loginResponse.data.token,
                ...userData
            }
            console.log("Login successful", loginResponse);
            authState.setAccount(user);
            navigate("/home");
        } catch (error) {
            if (isAxiosError(error)) {
                console.error(`Error: got response code ${error.code} at POST /register`);
            } else {
                console.error("Error at POST /register");
            }
            throw error;
        }
    }

    return (
        <Col className="d-flex justify-content-center">
            <Form ref={formRef} className="mt-4 col-md-4 d-flex align-items-stretch flex-column gap-4">
                <h1 className="text-center">CauseConnect</h1>

                <Form.Group controlId="formEmail">
                    <Form.Label>Email</Form.Label>
                    <Form.Control
                        type="email"
                        placeholder="Enter your email address"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                    />
                </Form.Group>

                <Form.Group controlId="formUsername">
                    <Form.Label>Username</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter your username"
                        minLength={4}
                        value={username}
                        onChange={e => setUsername(e.target.value)}
                    />
                </Form.Group>

                <Form.Group controlId="formPassword">
                    <Form.Label>Password</Form.Label>
                    <InputGroup>
                        <Form.Control
                            type={isPasswordVisible ? "text" : "password"}
                            value={password}
                            minLength={8}
                            onChange={e => setPassword(e.target.value)}
                            placeholder="Password"
                        />
                        <InputGroup.Text>
                            <FontAwesomeIcon
                                title="Toggle password visibility" icon={isPasswordVisible ? faEyeSlash : faEye}
                                onClick={() => setIsPasswordVisible(!isPasswordVisible)}
                            />
                        </InputGroup.Text>
                    </InputGroup>
                </Form.Group>

                <Button variant="primary" type="button" onClick={handleRegister}>
                    Register
                </Button>

                <div className="mt-2 d-flex flex-column text-center">
                    <em className="text-secondary fst-normal">Already have an account?</em>
                    <Link className="text-primary" to="/login">Log in instead</Link>
                </div>
            </Form>
        </Col>
    );
}
