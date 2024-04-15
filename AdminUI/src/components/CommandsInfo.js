export default function CommandsInfo()
{
    return(
        <div className="content-wrapper">
  {/* Content Header (Page header) */}
  <section className="content-header">
    <div className="container-fluid">
      <div className="row mb-2">
        <div className="col-sm-6">
          <h1>Router Commands</h1>
        </div>
      </div>
    </div>{/* /.container-fluid */}
  </section>
  {/* Main content */}
  <section className="content">
    <div className="row">
      <div className="col-12" id="accordion">
        <div className="card card-primary card-outline">
          <a className="d-block w-100" data-toggle="collapse" href="#collapseOne">
            <div className="card-header">
              <h4 className="card-title w-100">
                1. show version 
              </h4>
            </div>
          </a>
          <div id="collapseOne" className="collapse show" data-parent="#accordion">
            <div className="card-body">
            The "show version" command on a router provides key system information such as hardware, software version, uptime, and configuration register settings in a concise format.
            </div>
          </div>
        </div>
        <div className="card card-primary card-outline">
          <a className="d-block w-100" data-toggle="collapse" href="#collapseTwo">
            <div className="card-header">
              <h4 className="card-title w-100">
                2. show running-config
              </h4>
            </div>
          </a>
          <div id="collapseTwo" className="collapse" data-parent="#accordion">
            <div className="card-body">
            The "show running-config" command on a router displays the current active configuration settings of the device, including interface configurations, routing protocols, and security policies.            </div>
          </div>
        </div>
        <div className="card card-primary card-outline">
          <a className="d-block w-100" data-toggle="collapse" href="#collapseThree">
            <div className="card-header">
              <h4 className="card-title w-100">
                3. show interfaces status
              </h4>
            </div>
          </a>
          <div id="collapseThree" className="collapse" data-parent="#accordion">
            <div className="card-body">
            The "show interfaces status" command on a router provides a summary of interface status, including port, VLAN, speed, duplex, and status (up/down), aiding in network troubleshooting and monitoring.            </div>
          </div>
        </div>
        <div className="card card-primary card-outline">
          <a className="d-block w-100" data-toggle="collapse" href="#collapseFour">
            <div className="card-header">
              <h4 className="card-title w-100">
                4. show processes cpu history
              </h4>
            </div>
          </a>
          <div id="collapseFour" className="collapse" data-parent="#accordion">
            <div className="card-body">
            The "show processes cpu history" command on a router displays a historical graph of CPU utilization over time, assisting in identifying periods of high CPU usage and potential performance issues.
            </div>
          </div>
        </div>
        <div className="card card-primary card-outline">
          <a className="d-block w-100" data-toggle="collapse" href="#collapseFive">
            <div className="card-header">
              <h4 className="card-title w-100">
                5. show sysinfo
              </h4>
            </div>
          </a>
          <div id="collapseFive" className="collapse" data-parent="#accordion">
            <div className="card-body">
            The "show sysinfo" command on a router provides a concise summary of system information, including hardware platform, software version, memory usage, and uptime.
            </div>
          </div>
        </div>
        <div className="card card-primary card-outline">
          <a className="d-block w-100" data-toggle="collapse" href="#collapseSix">
            <div className="card-header">
              <h4 className="card-title w-100">
                6. show inventory
              </h4>
            </div>
          </a>
          <div id="collapseSix" className="collapse" data-parent="#accordion">
            <div className="card-body">
            The "show inventory" command on a router provides a detailed inventory list of hardware components installed, including module type, serial numbers, and physical location, aiding in device management and maintenance.
            </div>
          </div>
        </div>
        <div className="card card-primary card-outline">
          <a className="d-block w-100" data-toggle="collapse" href="#collapseSeven">
            <div className="card-header">
              <h4 className="card-title w-100">
                7. show acl summary
              </h4>
            </div>
          </a>
          <div id="collapseSeven" className="collapse" data-parent="#accordion">
            <div className="card-body">
            The "show acl summary" command on a router provides a summary of Access Control Lists (ACLs) configured on the device, including the number of ACLs, the number of entries per ACL, and their applied interfaces, aiding in network security management.            </div>
          </div>
        </div>
        <div className="card card-primary card-outline">
          <a className="d-block w-100" data-toggle="collapse" href="#collapseEight">
            <div className="card-header">
              <h4 className="card-title w-100">
                8. show ap summary
              </h4>
            </div>
          </a>
          <div id="collapseEight" className="collapse" data-parent="#accordion">
            <div className="card-body">
            The "show ap summary" command on a router provides a summary of Access Points (APs) connected to the device, including their status, model, and number of associated clients, aiding in wireless network management.
            </div>
          </div>
        </div>
        <div className="card card-primary card-outline">
          <a className="d-block w-100" data-toggle="collapse" href="#collapseNine">
            <div className="card-header">
              <h4 className="card-title w-100">
                9. show radius summary
              </h4>
            </div>
          </a>
          <div id="collapseNine" className="collapse" data-parent="#accordion">
            <div className="card-body">
            The "show radius summary" command on a router provides a summary of Remote Authentication Dial-In User Service (RADIUS) server configurations, including their status, IP addresses, and authentication methods, aiding in centralized authentication management.            </div>
          </div>
        </div>
        <div className="card card-primary card-outline">
          <a className="d-block w-100" data-toggle="collapse" href="#collapseTen">
            <div className="card-header">
              <h4 className="card-title w-100">
                10. show run-config commands
              </h4>
            </div>
          </a>
          <div id="collapseTen" className="collapse" data-parent="#accordion">
            <div className="card-body">
            The "show run-config commands" command on a router displays the commands used to generate the current running configuration, providing a quick overview of the configuration applied to the device.            </div>
          </div>
        </div>

        
        
        
      </div>
    </div>
    
  </section>
  {/* /.content */}
</div>


    )
}