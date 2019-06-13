import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button, Form, FormGroup, Input, Label} from "reactstrap";
import {attachRoomGrid} from "./Actions";
import {connect} from 'react-redux';
import {deleteRoomFromGrid} from "../US149/Actions";
import {fetchRoomsNotInGrid} from "../US147/ActionsGetRoomsNotInGrid"

class AttachRoomToGrid extends React.Component {

  constructor(props) {
    super(props);
  this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.state = {
      name: '',
      formerGrid:''
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };
  }

  handleSubmit() {
    this.props.onDeleteRoomFromGrid(this.state.name, this.state.formerGrid);
    this.props.onAttachRoomGrid(this.state.name, this.props.grid);
  }

  componentDidMount() {
    const grid = this.props;
    this.props.onGetRoomsNotInGrid(grid)
  }

  handleChange(event) {
    this.setState({
      name: event.target.value,
    });
  }

  render() {
    const {name} = this.state;
    const {roomsNotInGrid} = this.props;
    console.log(roomsNotInGrid)
    return (
      <>
        <Form action="" method="post" >
          <FormGroup>
            <Label>Select Room</Label>
            <Input type="select" name="select" id="select" value={this.state.value} onChange={this.handleChange}>
              <option value="0" onChange={this.handleChange}>Please select</option>
              {roomsNotInGrid.map(items => (
                <option value={items.name}  key={items.name}>
                  Name: {items.name}
                </option>
              ))}
            </Input>
          </FormGroup>
        </Form>
        {/*RoomID:<input value={this.state.name} type="text" name="name"*/}
                      {/*onChange={this.handleInputChange('name')}*/}
                      {/*/>*/}
        {/*Former Grid:<input value={this.state.formerGrid} type="text" name="formerGrid" onChange={this.handleInputChange('formerGrid')}/>*/}
        <p></p>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.handleSubmit}>Attach
          Room {name} to {this.props.grid}</Button>
      </>
    )
  }
}



const mapStateToProps = (state) => {
  return {
    loading: state.ReducersGetRoomsNotInGrid.loading,
    roomsNotInGrid: state.ReducersGetRoomsNotInGrid.roomsNotInGrid,
    error: state.ReducersGetRoomsNotInGrid.error
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
    onGetRoomsNotInGrid: (grid)=> {
      dispatch(fetchRoomsNotInGrid({grid}))
    },
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(AttachRoomToGrid);
