import Spinner from 'react-bootstrap/Spinner';

interface LoaderProps {
  text: string;
}

const Loader = ({text}: LoaderProps) => {
  return (
    <div className="d-flex justify-content-center align-items-center" style={{height: '100vh'}}>
      <Spinner animation="border" variant="primary" role="status" style={{marginRight: '8px'}}>
        <span className="sr-only">Loading...</span>
      </Spinner>
      {text && <span>{text}</span>}
    </div>
  );
};

export default Loader;
