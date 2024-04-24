
import { useState, useRef, useEffect } from "react"
import { useSelector } from "react-redux";
import TypeWriterEffect from 'react-typewriter-effect';

const userImg = <img src="/imgs/ProfileImg.png" className="direct-chat-img" />
const botImg = <img src='/imgs/BOT_logo.jpg' className="direct-chat-img" />


export default function ChatBotModal() {

    const username = useSelector((state) => state.user.userName)
    const deviceId = useSelector((state) => state.device.deviceId);
    const ref = useRef();
    const chatContainerRef = useRef(null);
    const [userQuery, setUserQuery] = useState('');
    const [messages, setMessages] = useState([]);

    function handleKeyPress(event) {
        if (event.key === 'Enter') {
            event.preventDefault(); 
            handleUserRequest(); 
        }
    }

    const fetchResponse = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/routers/${deviceId}/chat-bot`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ deviceId, username, userQuery }),
            });

            if (!response.ok) {
                throw new Error('Failed to add device');
            }

            const responseData = await response.text();
            console.log(responseData);
            setMessages((prevMsgState) => {
            console.log(prevMsgState);
                const updatedMsgState = [...prevMsgState, responseData];
                return updatedMsgState;
            })
        } catch (error) {
            console.error('Error adding device:', error);
        }
    }

    useEffect(() => {
        chatContainerRef.current.scrollTop = chatContainerRef.current.scrollHeight;
    }, [messages]);

    function handleInputChange(e) {
        setUserQuery(e.target.value);
    }

    function handleUserRequest() {
        ref.current.value = '';

        setMessages((prevMsgState) => {
            const updatedMsgState = [...prevMsgState, userQuery];
            return updatedMsgState;
        })

        fetchResponse();
    }

    function handleReset() {
        setMessages([]);
    }

    return (
        <div className="modal fade" id="modal-chatBot" style={{ height: '100%' }}>
            <div className="modal-dialog" style={{ maxWidth: '50%', height: '80%' }}>
                <div className="modal-content" style={{ maxWidth: '100%', height: '100%' }}>
                    <div className="modal-header" style={{ position: 'relative' }}>
                        <h4 className="modal-title">InsightBot</h4>
                        <ion-icon name="refresh" style={{ position: 'absolute', left: 'calc(82% + 10px)', top: '25%', height: '30%', width: '10%' }} onClick={handleReset}></ion-icon>
                        <button type="button" className="close" data-dismiss="modal">
                            <ion-icon name="close" style={{ height: '25px', width: '25px' }}></ion-icon>
                        </button>
                    </div>
                    <div className="card direct-chat direct-chat-warning" style={{ height: '100%', maxHeight: '100%' }}>
                        <div className="card-body" style={{ overflow: 'auto' }}>
                            <div className="direct-chat-messages" style={{ width: '100%', height: '84%', overflow: 'auto' }} ref={chatContainerRef}>

                                {messages.length ?
                                    messages.map((msg, index) => {

                                        return (
                                            index % 2 ? <div className="direct-chat-msg" key={index}>
                                                <div className="direct-chat-infos clearfix">
                                                    <span className="direct-chat-name float-left">InsightBot</span>
                                                </div>
                                                {botImg}
                                                <div className="direct-chat-text" >
                                                <TypeWriterEffect
                                                     textStyle={{ fontFamily: 'Arial' ,fontSize : '15px'}}
                                                     startDelay={10}
                                                     cursorColor = 'grey'
                                                     hideCursorAfterText = 'false'
                                                     text={msg}
                                                     typeSpeed={30}
                                                    />
                                                </div>
                                            </div> : <div className="direct-chat-msg right" key={index}>
                                                <div className="direct-chat-infos clearfix" >
                                                    <span className="direct-chat-name float-right">{username}</span>
                                                </div>
                                                {userImg}
                                                <div className="direct-chat-text"style={{backgroundColor:'#fcfcfc',color:'black', border:'#fcfcfc'}}>
                                                    {msg}
                                                </div>
                                            </div>

                                        )

                                    }) :
                                    <div style={{ display: 'flex', flexDirection: 'column', marginTop: '20%', alignItems: 'center' }}>
                                        <div>
                                            <h5>How Can I help You today?</h5>
                                            {/* <TypeWriterEffect
                                                textStyle={{ fontFamily: 'Arial' }}
                                                startDelay={3000}
                                                cursorColor="black"
                                                text="How Can I help You today?"
                                                typeSpeed={100}
                                                // scrollArea={myAppRef}
                                            /> */}
                                            
                                        </div>
                                        <div>
                                            <img src='/imgs/BOT_logo.jpg' className="direct-chat-img" />
                                        </div>
                                    </div>
                                }

                            </div>
                            <div className="card-footer" style={{ maxHeight: '80%' }} >
                                <form action="#" method="post">
                                    <div className="input-group">
                                        <input type="text" name="message" placeholder="Type Message ..." className="form-control" autoComplete="off" onChange={handleInputChange} ref={ref} onKeyPress={handleKeyPress}/>
                                        <div className="input-group-append">
                                            <button type="button" className="btn btn-warning" style={{ backgroundColor: '#fcfcfc', border: '#fcfcfc', color: 'black' }} onClick={handleUserRequest}>Send</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    {/* </div> */}
                    {/* </div> */}
                    {/* </div> */}
                </div>
            </div>
        </div>
    );
}
