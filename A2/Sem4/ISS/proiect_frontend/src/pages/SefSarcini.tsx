import {MyNavbar} from "../layouts/navbar";
import React, {useEffect} from "react";
import {User} from "../data/user";
import {AlertDismissibleExample} from "../components/alerts";
import {Sarcina} from "../data/sarcina";
import {API} from "../data/static";
import {SarcinaComp} from "../components/Sarcina";
import {Row} from "react-bootstrap";

export const SefSarcini = () => {
    const sef = React.useRef<User>();
    const [sarcini, setSarcini] = React.useState<Sarcina[]>([]);
    const [errorText, setErrorText] = React.useState<string>("");


    useEffect(() => {
        User.getUser().then((user) => {
            if(user !== null)
                sef.current = user;
            else window.location.href = "/";
        });
        fetch(API + '/sef_sarcini/', {
            credentials: 'include',
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
        }).then((response) => response.json())
            .then((data) => {
                var sarcinilist: Sarcina[] = Sarcina.deserializeArray(data)
                setSarcini(sarcinilist);
            })

    }, []);

    return (
        <div>
            {errorText.length > 0 && <AlertDismissibleExample text={errorText}/>}
            <MyNavbar sef={true} />
            <h1>Sef sarcini</h1>

            <Row sm={1} md={2} lg={3} xl={4}>
                {sarcini.map((sarcina) => <SarcinaComp key={sarcina.id} sarcina={sarcina} solvable={false}/>)}
            </Row>
        </div>
    )
}