import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button, Form, FormGroup, Input, Label} from "reactstrap";
import {attachRoomGrid} from "./Actions";
import {connect} from 'react-redux';
import {deleteRoomFromGrid} from "../US149/Actions";
import {fetchRoomsNotInGrid} from "../US147/ActionsGetRoomsNotInGrid"
import US253Post from "../../Room/RoomConfiguration/US253/US253Post";

class AttachRoomToGrid extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    //this.handleChange = this.handleChange.bind(this);
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

  toggleHidden = ()=>this.setState((prevState)=>({isHidden: !prevState.isHidden}))

  handleSubmit() {
    this.props.onDeleteRoomFromGrid(this.state.name, this.state.formerGrid);
    this.props.onAttachRoomGrid(this.state.name, this.props.grid);
  }

  // componentDidMount() {
  //   this.props.onGetRoomsNotInGrid(this.props.grid)
  // }

  // handleChange(event) {
  //   console.log(event.target.value.name2)
  //
  //   this.setState({
  //     value: event.target.value,
  //
  //   });
  // }

  render() {
    const {name} = this.state;
    const {room, error} = this.props;
    return (
      <>
        {/*<Form action="" method="post">*/}
          {/*<FormGroup>*/}
            {/*<Label>Select Room</Label>*/}
            {/*<Input type="select" name="select" id="select" value={this.state.value} onChange={this.handleChange}>*/}
              {/*<option value="0" onChange={this.handleChange}>Please select</option>*/}
              {/*{roomsNotInGrid.map(items => (*/}
                {/*<option value={{name2: items.name, formerGrid: items.gridID}} key={items.name}>*/}
                  {/*Name: {items.name} Grid: {items.gridID}*/}
                {/*</option>*/}
              {/*))}*/}
            {/*</Input>*/}
          {/*</FormGroup>*/}
        {/*</Form>*/}
        RoomID:<input value={this.state.name} placeholder="Ex: B107" type="text" name="name"
                      onChange={this.handleInputChange('name')}
      />
        Former Grid:<input value={this.state.formerGrid} placeholder="Ex: B building" type="text" name="formerGrid"
                           onChange={this.handleInputChange('formerGrid')}/>
        <p></p>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.handleSubmit && this.toggleHidden}>Attach
          Room {name} to {this.props.grid}</Button>
        {!this.state.isHidden && <h6>The room has been attached!</h6>}
      </>
    )

    if((room.toString()).indexOf("ERROR") != -1) {
      return(<div>
          <h6> ERROR: {error}.
          </h6>
        </div>
      )
    }  }
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
    // onGetRoomsNotInGrid: (grid) => {
    //   dispatch(fetchRoomsNotInGrid({grid}))
    // },
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(AttachRoomToGrid);
