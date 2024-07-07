import CauseProvider from "../components/cause/CauseProvider.tsx";
import GeneralCauses from "./GeneralCauses.tsx";

export default function Causes() {
  return (
    <>
        <CauseProvider>
          <GeneralCauses/>
        </CauseProvider>
    </>
  );
}