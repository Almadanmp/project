import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import AttachRoom from "./AttachRoom"
import {attachRoomGrid} from "./Actions";
import {connect} from 'react-redux';
import {deleteRoomFromGrid} from "../US149/Actions149Hateoas";

class AttachRoomToGrid extends React.Component {

  constructor(props) {
    super(props);
  //  this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      isHidden: true,
      formerGrid: '',
      name: '',
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };
  }

  toggleHidden = () => this.setState((prevState) => ({isHidden: !prevState.isHidden}))

  // handleSubmit() {
  //   this.props.onDeleteRoomFromGrid(this.state.name, this.state.formerGrid);
  //   this.props.onAttachRoomGrid(this.state.name, this.props.grid);
  // }

  render() {
    const {name} = this.state;
    return (
      <>
        Name:<input value={this.state.name} placeholder="Ex: B107" type="text" name="name"
                      onChange={this.handleInputChange('name')}
      />
        Former Grid:<input value={this.state.formerGrid} placeholder="Ex: B building" type="text" name="formerGrid"
                           onChange={this.handleInputChange('formerGrid')}/>
        <p></p>
        <p>
          <small>(Please type the name of the room and its previous grid. If the room does not belong to a grid, just
            write its name.)
          </small>
        </p>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}}
                onClick={this.toggleHidden}>Attach
          Room {name} to {this.props.grid}</Button>
        {!this.state.isHidden && <AttachRoom link={this.props.link} name={ this.state.name} grid={this.props.grid} formerGrid={this.state.formerGrid}/>}
      </>
    )
  }
}


const mapStateToProps = (state) => {
  return {
    // loading: state.ReducersGetRoomsNotInGrid.loading,
    // roomsNotInGrid: state.ReducersGetRoomsNotInGrid.roomsNotInGrid,
    // error: state.ReducersGetRoomsNotInGrid.error
    loading: state.Reducers147.loading,
    room: state.Reducers147.room,
    error: state.Reducers147.error
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onAttachRoomGrid: (name, grid) => {
      dispatch(attachRoomGrid({name, grid}))
    },
    onDeleteRoomFromGrid: (name, grid) => {
      dispatch(deleteRoomFromGrid({name, grid}))
    },
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(AttachRoomToGrid);
