import {MyNavbar} from "../layouts/navbar";
import React, {useEffect} from "react";
import {User} from "../data/user";
import {AlertDismissibleExample} from "../components/alerts";
import {API} from "../data/static";
import {Table} from "react-bootstrap";
import {ButtonAdaugaSarcina} from "../components/ButtonAdaugaSarcina";

export const SefAngajati = () => {
    const sef = React.useRef<User>();
    const [users, setUsers] = React.useState<User[]>([]);
    const [errorText, setErrorText] = React.useState<string>("");

    useEffect(() => {
        User.getUser().then((user) => {
            if (user !== null)
                sef.current = user;
            else
                window.location.href = "/";
        });

        fetch(API + '/sef_angajati/', {
            credentials: 'include',
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
        }).then((response) => response.json())
        .then((data) => {
            var userlist: User[] = User.fromJsonArray(data)
            setUsers(userlist)
        });


    }, []);

    return (
        <div>
            {errorText.length > 0 && <AlertDismissibleExample text={errorText}/>}
            <MyNavbar sef={true} />
            <h1>Sef Angajati</h1>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Nume</th>
                        <th>Prenume</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                {users.map((user) => {
                    return (
                        <tr key={user.id}>
                            <td>{user.username}</td>
                            <td>{user.last_name}</td>
                            <td>{user.first_name}</td>
                            <td>{user.email}</td>
                            <td><ButtonAdaugaSarcina user={user}/></td>
                        </tr>
                    )
                })}
                </tbody>
            </Table>
        </div>
    )
}