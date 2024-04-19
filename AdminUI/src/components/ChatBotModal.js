const userImg = <img src="dist/img/user2-160x160.jpg" className="direct-chat-img"/>
const botImg = <img src='/imgs/BOT_logo.jpg' className="direct-chat-img"/>
export default function ChatBotModal(){
    return (
        <div className="content-wrapper">
            <section className="content">
                <div className="container-fluid">
                    
                    <div className="row">
                        {/* Left col */}
                        <div className="col-md-8">
                            {/* MAP & BOX PANE */}
                            
                            {/* /.card */}
                            <div className="row">
                                <div className="justify-content-center col-md-9-8">
                                    <div className="card direct-chat direct-chat-warning">
                                        <div className="card-header">
                                            <h3 className="card-title ">InsightBot</h3>
                                            <div className="card-tools">                                                
                                                <button type="button" className="btn btn-tool" data-card-widget="remove">
                                                    <i className="fas fa-times" />
                                                </button>
                                            </div>
                                        </div>
                                        {/* /.card-header */}
                                        <div className="card-body">                                            <div className="direct-chat-messages">
                                                <div className="direct-chat-msg">
                                                    <div className="direct-chat-infos clearfix">
                                                        <span className="direct-chat-name float-left">Alexander Pierce</span>
                                                    </div>
                                                    {userImg}    
                                                    <div className="direct-chat-text">
                                                        Working with AdminLTE on a great new app! Wanna join?
                                                    </div>
                                                </div>
                                                
                                                <div className="direct-chat-msg right">
                                                    <div className="direct-chat-infos clearfix">
                                                        <span className="direct-chat-name float-right">InsightBot</span>                                                    </div>
                                                    {botImg }
                                                    <div className="direct-chat-text">
                                                    You better believe it!
                                                    </div>
                                                </div>

                                                <div className="direct-chat-msg">
                                                    <div className="direct-chat-infos clearfix">
                                                        <span className="direct-chat-name float-left">Alexander Pierce</span>
                                                    </div>
                                                    {userImg}
                                                    <div className="direct-chat-text">
                                                        Working with AdminLTE on a great new app! Wanna join?
                                                    </div>
                                                </div>
                                                
                                                <div className="direct-chat-msg right">
                                                    <div className="direct-chat-infos clearfix">
                                                        <span className="direct-chat-name float-right">InsightBot</span>                                                    </div>
                                                        {botImg }                                                    <div className="direct-chat-text">
                                                    You better believe it!
                                                    </div>
                                                </div>
                                            </div>
                            
                                        </div>
                                        {/* /.card-body */}
                                        <div className="card-footer">
                                            <form action="#" method="post">
                                                <div className="input-group">
                                                    <input type="text" name="message" placeholder="Type Message ..." className="form-control" autoComplete="off" />
                                                    <span className="input-group-append">
                                                        <button type="button" className="btn btn-warning">Send</button>
                                                    </span>
                                                </div>
                                            </form>
                                        </div>
                                        {/* /.card-footer*/}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>{/*/. container-fluid */}
            </section>
            {/* /.content */}
        </div>

    )
}