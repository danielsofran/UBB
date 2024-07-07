import {Navigate, useParams} from 'react-router-dom'
import React, {useContext, useEffect, useState} from "react";
import {Alert, Button, Container, Form} from "react-bootstrap";
import {causeService} from "../service/causes";
import {Cause as CauseModel} from "../model/cause";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faImage, faSave} from "@fortawesome/free-solid-svg-icons";
import {useNavigate} from "react-router-dom";
import AppCarousel from "../components/core/AppCarousel";
import FileUploader from "../components/core/FileUploader";
import {AuthContext} from "../components/util/AuthProvider";


interface FormErrors {
  location?: string;
  description?: string;
  name?: string;
  sources?: string;
}


export default function CauseEdit() {
  const navigate = useNavigate();
  const id = useParams().id as unknown as number;
  const [cause, setCause] = useState<CauseModel>(new CauseModel());
  const [sources, setSources] = useState<string[]>([]);
  const authState = useContext(AuthContext);

  const [errors, setErrors] = useState({} as FormErrors);

  const showErrors = (errors: FormErrors) => {
    setErrors(errors);
    window.scrollTo(0, 0);
  }

  useEffect(() => {
    causeService.getCausePhotos(id, authState.account?.token).then(photos => {
      setSources(photos.map(causeService.getPhotoUrl))
    })
    causeService.getOne(id, authState.account?.token).then(cause => {
      setCause(cause)
    })
  }, [id, authState.account?.token])

  const addSource = (src: FileList) => {
    const files = Array.from(src)
    setSources([...sources, ...files.map(URL.createObjectURL)])
  }

  const onSubmit = async (e: any) => {
    e.preventDefault()

    const localErrors: FormErrors = {};
    if(cause.location.length == 0) {
      localErrors.location = "Location is required.";
    }
    if(cause.description.length == 0) {
      localErrors.description = "Description is required.";
    }
    if(cause.name.length == 0) {
      localErrors.name = "Name is required.";
    }
    if(sources.length == 0) {
      localErrors.sources = "At least one photo is required.";
    }

    if(Object.keys(localErrors).length > 0) {
      console.log(localErrors)
      showErrors(localErrors);
      return;
    }

    causeService.uploadCausePhotos(id, sources, authState.account?.token)
      .then(() => {
        causeService.put(id, cause, authState.account?.token)
          .then(() => {
            navigate(`/cause/${id}`)
          })
      }).catch(err => {
      console.log(err)
      alert(err)
    })

  }

  return (
    <Container>
      {Object.keys(errors).length > 0 &&
          <Alert variant="danger">
              Please fill in all required fields before submitting the form.
              <ul>
                {Object.keys(errors).map(key => (
                  // @ts-ignore
                  <li>{errors[key]}</li>
                ))}
              </ul>
          </Alert>}

      {cause.userId != -1 && authState.account?.id !== cause.userId &&
          <Navigate to="/home"/>
      }
      <Form>
        <Form.Group className="mb-3">
          <Form.Label>Cause Name</Form.Label>
          <Form.Control isInvalid={!!errors.name} type="text" placeholder="Enter cause name" value={cause.name}
                        onChange={e => setCause({...cause, name: e.target.value})}/>
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Description</Form.Label>
          <Form.Control isInvalid={!!errors.description} as="textarea" rows={3} placeholder="Enter description" value={cause.description}
                        onChange={e => setCause({...cause, description: e.target.value})}/>
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Location</Form.Label>
          <Form.Control isInvalid={!!errors.location} type="text" placeholder="Enter location" value={cause.location}
                        onChange={e => setCause({...cause, location: e.target.value})}/>
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Cause Type</Form.Label>
          <Form.Select aria-label="Cause Type" value={cause.causeType}
                       onChange={e => setCause({...cause, causeType: e.target.value})}>
            <option value="Personal">Personal</option>
            <option value="Organization">Organization</option>
          </Form.Select>
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label> Money Target </Form.Label>
          <Form.Control type="number" placeholder="Enter money target" value={cause.moneyTarget}
                        onChange={e => setCause({...cause, moneyTarget: Number(e.target.value)})}/>
          <Form.Range min={0} max={10000} value={cause.moneyTarget}
                      onChange={e => setCause({...cause, moneyTarget: Number(e.target.value)})}/>
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label> Deadline </Form.Label>
          <Form.Control type="date" placeholder="Enter deadline" value={cause.deadline?.toISOString().slice(0, 10)}
                        onChange={e => setCause({...cause, deadline: new Date(e.target.value)})}/>
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label> Photos </Form.Label>
          <div className="d-flex justify-content-center align-items-center">
            <FileUploader onUpload={(files) => addSource(files)} accept="image/*">
              <Button className="mb-1" variant="primary">
                <FontAwesomeIcon icon={faImage} className="me-1"/>
                Add Photo
              </Button>
            </FileUploader>
          </div>
          <div className="d-block">
            {sources.length === 0 ?
              <h4 className="text-muted text-center">No photos selected</h4> :
              <AppCarousel sources={sources} setSources={setSources} editable={true}
                           imageStyle={{width: "80%", height: "45vh"}}/>
            }
          </div>
        </Form.Group>
        {authState.account?.id === cause.userId &&
            <div className="d-flex justify-content-center align-items-center">
                <Button variant="primary" size="lg" type="submit" onClick={(e) => onSubmit(e)}>
                    <FontAwesomeIcon icon={faSave} className="me-1"/>
                    Save
                </Button>
            </div>
        }
      </Form>
    </Container>
  )
}