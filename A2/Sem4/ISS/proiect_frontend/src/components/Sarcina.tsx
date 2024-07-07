import { Sarcina } from '../data/sarcina';
import {Button, Card} from "react-bootstrap";
import {showDateTime} from "../data/utils";
import {API} from "../data/static";
import {getCookie} from "../data/DjangoCsrfToken";
import {useState} from "react";

export const SarcinaComp = (props: {sarcina: Sarcina, solvable: boolean}) => {
    const [showing, setShowing] = useState<boolean>(true);

    const completetSymbol = () => {
        if (props.sarcina.completed)
            return <span className="badge bg-success">Completata</span>
        else
            return <span className="badge bg-warning">Incompleta</span>
    }

    const solveSarcina = () => {
        fetch(`${API}/sarcina/${props.sarcina.id}/`, {
            credentials: 'include',
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRFToken': getCookie('csrftoken'),
            }
        }).then((response) => { return response.json() }).then((data) => {
            if (data.error) {
                alert(data.error);
            } else {
                window.location.href = "/home_angajat";
            }
        });
    }

    const deleteSarcina = () => {
        fetch(`${API}/sarcina/${props.sarcina.id}/`, {
            credentials: 'include',
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRFToken': getCookie('csrftoken'),
            }
        }).then((response) => { return response.json() }).then((data) => {
            if (data.error) {
                alert(data.error);
            } else {
                setShowing(false)
            }
        });
    }

    return (
        <>
            {
                !showing ? <div /> :
                    <Card>
                        <Card.Header>{completetSymbol()}</Card.Header>
                        <Card.Body>
                            <Card.Text> {props.sarcina.cerinta} </Card.Text>
                            <Card.Text> Proposed on: {showDateTime(props.sarcina.datetime)} </Card.Text>
                        </Card.Body>

                        <Card.Footer>

                            <div className="d-flex flex-row justify-content-around">
                                {props.solvable && !props.sarcina.completed &&
                                    <Button className="btn btn-success" onClick={solveSarcina}>Solve</Button>}
                                {!props.solvable &&
                                    <Button className="btn btn-danger" onClick={deleteSarcina}>Sterge</Button>}
                            </div>

                        </Card.Footer>
                    </Card>
            }
        </>
    );
}