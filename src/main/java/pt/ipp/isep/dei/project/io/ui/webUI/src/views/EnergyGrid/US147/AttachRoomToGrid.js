import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import {attachRoomGrid} from "./Actions";
import {connect} from 'react-redux';

class AttachRoomToGrid extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      name: '',
      grid: this.props.grid,
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };
  }

  handleSubmit() {
    this.props.onAttachRoomGrid(this.state);
  }


  render() {
    const {name} = this.state;
    return (
      <>
        RoomID:<input value={this.state.name} type="text" name="name" onChange={this.handleInputChange('name')}/>
        <p></p>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.handleSubmit}>Attach
          Room {name} to {this.props.grid}</Button>
      </>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onAttachRoomGrid: ({name, grid}) => {
      dispatch(attachRoomGrid({name, grid}))
    }
  }
};

export default connect(null, mapDispatchToProps)(AttachRoomToGrid);
