import {API} from "../data/static";
import {useEffect, useState} from 'react';
import {Alert, Button, Form} from 'react-bootstrap';
import {getCookie} from "../data/DjangoCsrfToken";
import {redirect} from "react-router-dom";
import {AlertDismissibleExample} from "./alerts";
import {User, UserType} from "../data/user";

export function LoginForm() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [success, setSuccess] = useState('');
    const [error, setError] = useState('');

    useEffect(() => {
        User.getUser().then((user: User|null) => {
            if (user !== null)
                switch (user.type) {
                    case UserType.Admin:
                        window.location.href = API+"/admin";
                        break;
                    case UserType.Sef:
                        window.location.href = "/sef_angajati";
                        break;
                    case UserType.Angajat:
                        window.location.href = "/home_angajat";
                        break;
                }
        });
    }, [success, error]);

    const handleSubmit = async (e: any) => {
        e.preventDefault();

        const response = await fetch(API+'/login/', {
            credentials: 'include',
            method: 'POST',
            mode: 'cors',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-CSRFToken': getCookie('csrftoken'),
            },
            body: JSON.stringify({ username, password }),
        });

        const data = await response.json();
        if (response.ok) {
            setSuccess(data.role);
        } else {
            setError(data.error);
        }
    };

    return (
        <div className="container">
            <Form onSubmit={(e) => handleSubmit(e)}>
                <Form.Group controlId="formBasicUsername">
                    <Form.Label>Username</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter username"
                        name="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="Password"
                        name="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </Form.Group>

                {success !== '' && <Alert variant="success">{success}</Alert>}
                {error !== '' && <AlertDismissibleExample text={error}/>}

                <Button className='mt-1' variant="primary" type="submit">
                    Log In
                </Button>

                {/*<Button variant="outline-dark" type="button" onClick={(e) => handleLogout(e)}>*/}
                {/*    Log Out*/}
                {/*</Button>*/}
            </Form>
        </div>
    );
}