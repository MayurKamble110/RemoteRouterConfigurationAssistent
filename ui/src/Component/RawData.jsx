import { useSelector } from "react-redux";
import { useEffect } from "react";
import { useState } from "react";
import { useRef } from "react";
import BasicModal from "./PopUpRefresh";

function RawData()
{   
    const id = useSelector((state) => state.device.deviceClicked);
  

    return(
        <>
        { id ? <h2>Raw data of device with ID : {id}</h2> : <BasicModal text="Something went wrong" description="Redirecting to the home page"></BasicModal>}
        </>
    )
}

export default RawData;