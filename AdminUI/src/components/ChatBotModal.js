
import { useState, useRef, useEffect } from "react"

const userImg = <img src="dist/img/user2-160x160.jpg" className="direct-chat-img" />
const botImg = <img src='/imgs/BOT_logo.jpg' className="direct-chat-img" />


export default function ChatBotModal(props) {

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

    return (
        <div className="modal fade " id="modal-chatBot">
            <div className="modal-dialog" style={{ maxWidth: '50%' }}>
                <div className="modal-content" style={{ height: '80%' }}>
                    <div className="modal-header">
                        <h4 className="modal-title">InsightBot</h4>
                        <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div className="col-md-8" style={{ maxWidth: '100%', maxHeight: '50%' }}>
                        {/* <div className="row"> */}
                        <div className="justify-content-center col-md-9-8">
                            <div className="card direct-chat direct-chat-warning">
                                <div className="card-body">
                                    <div className="direct-chat-messages" style={{ width: '100%' }}>

                                        {
                                            messages.map((msg, index) => {

                                                return (
                                                    index % 2 ? <div className="direct-chat-msg right">
                                                        <div className="direct-chat-infos clearfix">
                                                            <span className="direct-chat-name float-left">InsightBot</span>
                                                        </div>
                                                        {botImg}
                                                        <div className="direct-chat-text">
                                                            {msg}
                                                        </div>
                                                    </div> : <div className="direct-chat-msg">
                                                        <div className="direct-chat-infos clearfix">
                                                            <span className="direct-chat-name float-right">Alexander Pierce</span>
                                                        </div>
                                                        {userImg}
                                                        <div className="direct-chat-text">
                                                            {msg}
                                                        </div>
                                                    </div>

                                                )

                                            })
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