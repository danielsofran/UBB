import CauseProvider from "../components/cause/CauseProvider";
import GeneralCauses from "./GeneralCauses";

export default function Causes() {
  return (
    <>
        <CauseProvider>
          <GeneralCauses/>
        </CauseProvider>
    </>
  );
}