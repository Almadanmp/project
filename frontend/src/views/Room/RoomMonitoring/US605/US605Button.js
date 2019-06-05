import React, {Component} from 'react';
import { Button} from 'reactstrap';
import US605GetCurrentTemperature from "./US605GetCurrentTemperature";


class US605Button extends Component {

  constructor(props) {
    super(props);
    this.state = {
      isHidden: true,
      roomID: ''
    }
  }

  toggleHidden = ()=>this.setState((prevState)=>({isHidden: !prevState.isHidden}))
  render(){
    return(
      <div>
     <Button onClick={this.toggleHidden}>Get Current Temperature</Button>
        {!this.state.isHidden && <US605GetCurrentTemperature roomID = {this.props.roomID}/>}
      </div>
    )
  }
}

export default US605Button;
