import React, { useState } from 'react';
import { Alert } from 'react-bootstrap';

export const AlertDismissibleExample: React.FC<{text: string}> = ({text}) => {
    const [show, setShow] = useState(true);

    if (show) {
        return (
            <Alert variant="danger" onClose={() => setShow(false)} dismissible>
                <Alert.Heading>Eroare!</Alert.Heading>
                <p>{text}</p>
            </Alert>
        );
    }
    return <span></span>;
}