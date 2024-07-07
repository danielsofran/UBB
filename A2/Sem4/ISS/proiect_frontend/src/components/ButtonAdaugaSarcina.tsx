import React from "react";
import {User} from "../data/user";
import {Button} from "react-bootstrap";

export const ButtonAdaugaSarcina: React.FC<{user: User}> = ({user}) => {
    return (
        <Button variant="success" onClick={() => {
            window.location.href = `/adauga_sarcina/${user.id}/`;
        }
        }>Adauga sarcina</Button>
    );
}