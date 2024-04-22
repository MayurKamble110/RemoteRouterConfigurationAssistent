
import { useState, useRef, useEffect } from "react"
import { useSelector } from "react-redux";

const userImg = <img src="dist/img/user2-160x160.jpg" className="direct-chat-img" />
const botImg = <img src='/imgs/BOT_logo.jpg' className="direct-chat-img" />


export default function ChatBotModal() {

    const user = useSelector((state) => state.user.userName)
    const ref = useRef();
    const [input, setInput] = useState('');
    const [messages, setMessages] = useState([]);

    function handleInputChange(e) {
        setInput(e.target.value);
    }

    function handleUserRequest() {
        ref.current.value = '';

        setMessages((prevMsgState) => {
            const updatedMsgState = [...prevMsgState, input];
            return updatedMsgState;
        })

        setMessages((prevMsgState) => {
            const updatedMsgState = [...prevMsgState, "Hello everyone I am InsightBot"];
            return updatedMsgState;
        })
    }

    function handleReset() {
        setMessages([]);
    }

    return (
        <div className="modal fade " id="modal-chatBot">
            <div className="modal-dialog" style={{ maxWidth: '50%' }}>
                <div className="modal-content" style={{ height: '80%' }}>
                    <div className="modal-header">
                        <h4 className="modal-title">InsightBot</h4>
                        <ion-icon name="refresh" style={{ position: 'absolute', right: '5%', top: '4%', height: '25px', width: '25px' }} onClick={handleReset}></ion-icon>
                        <button type="button" className="close" data-dismiss="modal">
                            <ion-icon name="close" style={{ height: '25px', width: '25px' }}></ion-icon>
                        </button>
                    </div>
                    <div className="col-md-8" style={{ maxWidth: '100%', maxHeight: '50%' }}>
                        {/* <div className="row"> */}
                        <div className="justify-content-center col-md-9-8">
                            <div className="card direct-chat direct-chat-warning">
                                <div className="card-body">
                                    <div className="direct-chat-messages" style={{ width: '100%' }}>

                                        {messages.length ?
                                            messages.map((msg, index) => {

                                                return (
                                                    index % 2 ? <div className="direct-chat-msg right" key={index}>
                                                        <div className="direct-chat-infos clearfix">
                                                            <span className="direct-chat-name float-left">InsightBot</span>
                                                        </div>
                                                        {botImg}
                                                        <div className="direct-chat-text">
                                                            {msg}
                                                        </div>
                                                    </div> : <div className="direct-chat-msg" key={index}>
                                                        <div className="direct-chat-infos clearfix">
                                                            <span className="direct-chat-name float-right">{user}</span>
                                                        </div>
                                                        {userImg}
                                                        <div className="direct-chat-text">
                                                            {msg}
                                                        </div>
                                                    </div>

                                                )

                                            }) :
                                            <div >

                                                <h5 style={{ marginLeft: '45%', marginTop: '8%' }}>How Can I help You today?</h5>
                                                <img src='/imgs/BOT_logo.jpg' className="direct-chat-img" style={{ marginLeft: '52%' }} />
                                            </div>
                                        }

                                    </div>
                                    <div className="card-footer" style={{ maxHeight: '80%' }} >
                                        <form action="#" method="post">
                                            <div className="input-group">
                                                <input type="text" name="message" placeholder="Type Message ..." className="form-control" autoComplete="off" onChange={handleInputChange} ref={ref} />
                                                <span className="input-group-append">
                                                    <button type="button" className="btn btn-warning" onClick={handleUserRequest}>Send</button>
                                                </span>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        {/* </div> */}
                    </div>
                </div>
            </div>
        </div>
    )
}