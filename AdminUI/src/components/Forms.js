export default function Forms(){
    return(
<div className="content-wrapper">
  {/* Main content */}
  <section className="content">
    <div className="container-fluid">
        {/* left column */}
        <div className="col-md-6">
          {/* general form elements */}
          <div className="card card-primary ">
            <div className="card-header card card-primary d-flex align-items-center justify-content-center">
              <h3 className="card-title">Add Device</h3>
            </div>
            {/* /.card-header */}
            {/* form start */}
            <form>
              <div className="card-body">
                <div className="form-group">
                  <label>Device Name</label>
                  <input type="text" className="form-control" id="exampleInputEmail1" placeholder="Enter device name" />
                </div>
                <div className="form-group">
                  <label>IP Address</label>
                  <input type="text" className="form-control" id="exampleInputEmail1" placeholder="Enter IP Address" />
                </div>
                <div className="form-group">
                  <label>Username</label>
                  <input type="text" className="form-control" id="exampleInputEmail1" placeholder="Enter username" />
                </div>
                <div className="form-group">
                  <label htmlFor="exampleInputPassword1">Login Password</label>
                  <input type="password" className="form-control" id="exampleInputPassword1" placeholder="Login Password" />
                </div>
                <div className="form-group">
                  <label htmlFor="exampleInputPassword1">Enable Password</label>
                  <input type="password" className="form-control" id="exampleInputPassword1" placeholder="Enable Password" />
                </div>
              </div>
              {/* /.card-body */}
              <div className="card-footer text-center">
                <button type="submit" className="btn btn-primary">Submit</button>
              </div>
            </form>
          </div>
          
          {/* /.card */}
        </div>
    </div>{/* /.container-fluid */}
  </section>
  {/* /.content */}
</div>

    )
}