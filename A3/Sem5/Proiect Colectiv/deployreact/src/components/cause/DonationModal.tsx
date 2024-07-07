import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import {Alert, Form, InputGroup, Row} from "react-bootstrap";
import {useContext, useState} from "react";
import {causeService} from "../../service/causes";
import {AuthContext} from "../util/AuthProvider";
import {Cause} from "../../model/cause";

interface DonationModalProps {
  show: boolean;
  setShow: (show: boolean) => void;
  causeId: number;
  setCause: (cause: Cause) => void;
}

interface FormErrors {
  firstName?: string;
  lastName?: string;
  cardNumber?: string;
  expirationDate?: string;
  cvc?: string;
  billingAddress1?: string;
  billingAddress2?: string;
  donationAmount?: string;
}

function DonationModal({show, setShow, causeId, setCause}: DonationModalProps) {
  const [firstName, setFirstName] = useState<string>('');
  const [lastName, setLastName] = useState<string>('');
  const [cardNumber, setCardNumber] = useState<string>('');
  const [expirationDate, setExpirationDate] = useState<string>('');
  const [cvc, setCvc] = useState<string>('');
  const [billingAddress1, setBillingAddress1] = useState<string>('');
  const [billingAddress2, setBillingAddress2] = useState<string>('');
  const [donationAmount, setDonationAmount] = useState<number>(0);

  const [errors, setErrors] = useState<FormErrors>({} as FormErrors);
  const authState = useContext(AuthContext);

  const showErrors = (errors: FormErrors) => {
    setErrors(errors);
  }

  const clearForm = () => {
    setFirstName('');
    setLastName('');
    setCardNumber('');
    setExpirationDate('');
    setCvc('');
    setBillingAddress1('');
    setBillingAddress2('');
    setDonationAmount(0);

    setErrors({} as FormErrors);
  }

  const assignFormattedCardNumber = (cardNumber: string) => {
    let formattedCardNumber = cardNumber.replace(/\D/g, '').replace(/(.{4})/g, '$1 ').trim();
    setCardNumber(formattedCardNumber);
  }

  const assignFormattedExpirationDate = (newExpirationDate: string) => {
    let formattedExpirationDate = newExpirationDate;
    if (!(newExpirationDate.length < expirationDate.length && newExpirationDate.charAt(newExpirationDate.length - 1) !== '/')) {
      formattedExpirationDate = newExpirationDate.replace(/\D/g, '').replace(/(.{2})/g, '$1/').trim();
    }

    formattedExpirationDate = formattedExpirationDate.substring(0, 5);

    if (newExpirationDate.length >= 2 && newExpirationDate.charAt(0) === '1' && Number(newExpirationDate.charAt(1)) > 2
      || newExpirationDate.length >= 2 && Number(newExpirationDate.charAt(0)) > 1) {
      formattedExpirationDate = expirationDate;
    }

    if(newExpirationDate.length >= 3 && !newExpirationDate.includes('/') && expirationDate.includes('/')) {
        formattedExpirationDate = expirationDate;
    }

    if(newExpirationDate.indexOf('/') !== 2 && newExpirationDate.includes('/')) {
      formattedExpirationDate = expirationDate;
    }
    setExpirationDate(formattedExpirationDate);
  }

  const formatAlphaString = (str: string) => {
    return str.replace(/[^a-z- A-Z]/g, '');
  }

  const handleClose = () => {
    setShow(false);
    clearForm();
  }

  const onDonate = () => {
    const localErrors: FormErrors = {};
    if (firstName.length == 0) {
      localErrors.firstName = "First name is required.";
    }
    if (lastName.length == 0) {
      localErrors.lastName = "Last name is required.";
    }
    if (/([0-9]{4} ){3}([0-9]{4})/.test(cardNumber) === false) {
      localErrors.cardNumber = "Card number must be 16 digits.";
    }
    if (cardNumber.length == 0) {
      localErrors.cardNumber = "Card number is required.";
    }
    if (/(0[1-9]|1[0-2])\/([0-9]{2})/.test(expirationDate) === false) {
      localErrors.expirationDate = "Expiration date must be in the format MM/YY.";
    }
    if (expirationDate.length == 0) {
      localErrors.expirationDate = "Expiration date is required.";
    }
    if (/[0-9]{3}/.test(cvc) === false) {
      localErrors.cvc = "CVC must be 3 digits.";
    }
    if (cvc.length == 0) {
      localErrors.cvc = "CVC is required.";
    }
    if (billingAddress1.length == 0) {
      localErrors.billingAddress1 = "Billing address line 1 is required.";
    }
    if (donationAmount <= 0) {
      localErrors.donationAmount = "You cannot donate $0 or less.";
    }

    if (Object.keys(localErrors).length > 0) {
      console.log(localErrors)
      showErrors(localErrors);
      return;
    }

    // Handle the donation logic here, e.g., make an API call to process the donation
    causeService.donateToCause(causeId, donationAmount, authState.account?.token)
      .then((newCause) => {
        setCause(newCause);
      })
      .catch(err => {
        console.log(err)
        alert(err)
      });

    // Close the modal after donation
    handleClose();
  };

  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title className="text-center">Donate to Cause</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        {
          Object.keys(errors).length > 0 &&
            <Alert variant="danger">
                Please fill in all required fields before submitting the form.
                <ul>
                  {Object.keys(errors).map(key => (
                    // @ts-ignore
                    <li>{errors[key]}</li>
                  ))}
                </ul>
            </Alert>
        }
        <Form>
          <Form.Group controlId="donationAmount">
            <Form.Label>Donation Amount ($)</Form.Label>
            <InputGroup className="mb-3">
              <Form.Control
                isInvalid={!!errors.donationAmount}
                type="number"
                min={0}
                placeholder="Enter donation amount"
                value={donationAmount.toString()}
                onChange={(e) => {
                  console.log(Number(e.target.value));
                  setDonationAmount(Number(e.target.value));
                }}
              />
              <InputGroup.Text id="basic-addon1">$</InputGroup.Text>
            </InputGroup>
          </Form.Group>
          <Form.Group controlId="firstNameLastName" as={Row} className="mb-3">
            <Form.Group controlId="firstName" className="col">
              <Form.Label>First Name</Form.Label>
              <Form.Control
                isInvalid={!!errors.firstName}
                type="text"
                placeholder="Enter first name"
                value={firstName}
                onChange={(e) => setFirstName(formatAlphaString(e.target.value))}
              />
            </Form.Group>
            <Form.Group controlId="lastName" className="col">
              <Form.Label>Last Name</Form.Label>
              <Form.Control
                isInvalid={!!errors.lastName}
                type="text"
                placeholder="Enter last name"
                value={lastName}
                onChange={(e) => setLastName(formatAlphaString(e.target.value))}
              />
            </Form.Group>
          </Form.Group>
          <Form.Group controlId="cardNumber" className="mb-3">
            <Form.Label>Card Number</Form.Label>
            <Form.Control
              isInvalid={!!errors.cardNumber}
              type="text"
              placeholder="Enter card number"
              value={cardNumber}
              maxLength={19}
              onChange={(e) => assignFormattedCardNumber(e.target.value)}
            />
          </Form.Group>
          <Row className="mb-3">
            <Form.Group controlId="expirationDate" className="col-md-6">
              <Form.Label>Expiration Date (MM/YY)</Form.Label>
              <Form.Control
                isInvalid={!!errors.expirationDate}
                type="text"
                placeholder="Enter expiration date"
                value={expirationDate}
                maxLength={5}
                onChange={(e) => assignFormattedExpirationDate(e.target.value)}
              />
            </Form.Group>
            <Form.Group controlId="cvc" className="col-md-6">
              <Form.Label>CVC</Form.Label>
              <Form.Control
                isInvalid={!!errors.cvc}
                type="text"
                placeholder="Enter CVC"
                value={cvc}
                maxLength={3}
                onChange={(e) => setCvc(e.target.value)}
              />
            </Form.Group>
          </Row>
          <Form.Group controlId="billingAddress1" className="mb-3">
            <Form.Label>Billing Address Line 1</Form.Label>
            <Form.Control
              isInvalid={!!errors.billingAddress1}
              type="text"
              placeholder="Enter billing address line 1"
              value={billingAddress1}
              onChange={(e) => setBillingAddress1(e.target.value)}
            />
          </Form.Group>
          <Form.Group controlId="billingAddress2" className="mb-3">
            <Form.Label>Billing Address Line 2</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter billing address line 2"
              value={billingAddress2}
              onChange={(e) => setBillingAddress2(e.target.value)}
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Close
        </Button>
        <Button variant="primary" onClick={onDonate}>
          Donate
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default DonationModal;