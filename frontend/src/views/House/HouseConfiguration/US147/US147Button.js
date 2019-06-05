import React, {Component} from 'react';
import {Button} from 'reactstrap';
import US147GetResult from "./US147GetResult";


class US147Button extends Component {

  constructor(props) {
    super(props);
    this.state = {
      isHidden: true,
      roomID: '',
      gridID: ''

    }
  }

  toggleHidden = () => this.setState((prevState) => ({isHidden: !prevState.isHidden}))

  render() {
    return (
      <div>
        <Button onClick={this.toggleHidden}>Submit</Button>
        {!this.state.isHidden && <US147GetResult roomID={this.props.roomID} gridId={this.props.gridID}/>}
      </div>
    )
  }
}

export default US147Button;
