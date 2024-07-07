import {Button, Container, Form} from "react-bootstrap";
import {User} from "../data/user";
import {useEffect, useState} from "react";
import {AlertDismissibleExample} from "../components/alerts";
import {API} from "../data/static";
import {getCookie, getCsrfToken} from "../data/DjangoCsrfToken";
import {useParams} from "react-router-dom";

export const AddSarcina = () => {
    const { id } = useParams<{ id: string }>();
    const [cerinta, setCerinta] = useState<string>("");
    const [errorText, setErrorText] = useState<string>("");

    useEffect(() => {
        // fetch(`${API}/angajati/`, {
        //     credentials: 'include',
        //     method: 'GET',
        //     headers: {'Content-Type': 'application/json'},
        // }).then((response) => response.json()).then((data) => {
        //     let angajati: User[] = User.fromJsonArray(data);
        //     setAngajati(angajati);
        //     console.log(data)
        // });
        getCsrfToken();
    }, []);

    const handleSubmit = (event: any) => {
        event.preventDefault();
        const data = {
            "angajat": id,
            "cerinta": cerinta,
        };
        fetch(`${API}/sarcina/add/`, {
            credentials: 'include',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRFToken': getCookie('csrftoken'),
            },
            body: JSON.stringify(data),
        }).then((response) => { return response.json() }).then((data) => {
            if (data.error) {
                setErrorText(data.error);
            } else {
                window.location.href = "/sef_sarcini";
            }
        });

    }

    return (
        <Container>
            <h1>Adauga sarcina</h1>
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="cerinta">
                    <Form.Label>Cerinta</Form.Label>
                    <Form.Control as="textarea"
                                  rows={3}
                                  name="descriere"
                                  value={cerinta}
                                  required={true}
                                  onChange={(event) => setCerinta(event.target.value)}
                    />
                </Form.Group>

                {errorText.length > 0 && <AlertDismissibleExample text={errorText}/>}
                <Button className="mt-3" variant="primary" type="submit"> Trimite sarcina </Button>
            </Form>
        </Container>
    )
}
