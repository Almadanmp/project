import React from 'react';
import {connect} from 'react-redux';
import {addSensorType} from './Actions005';
import {Button} from "reactstrap";
import CardBody from "reactstrap/es/CardBody";
import Card from "reactstrap/es/Card";

class US005Redux extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      name: '',
      units: ''
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };

  }

  handleSubmit() {
    this.props.onPostType(this.state);
  }

  render() {
    const {name, units} = this.state;
    return (
      <div className="animated fadeIn">
        <Card><CardBody> Name:<span>  </span>
          <input  value={this.state.name} type="text" name="name" placeholder="Name of the new sensor type"
                 onChange={this.handleInputChange('name')}/>

        <p></p>
        <label> Units:<span>  </span>
          <input value={this.state.units} type="text" name="units" placeholder="Unit measure used for this type"
                 onChange={this.handleInputChange('units')}/>
        </label>
        <p/>
        <Button style={{ marginBottom: '1rem'}} onClick={this.handleSubmit}>Add sensor
          type</Button>
        </CardBody>
        </Card>
      </div>
    );
  }
}


const mapDispatchToProps = (dispatch) => {
  return {
    onPostType: ({name, units}) => {
      dispatch(addSensorType(name, units))
    }
  }
};

export default connect(
  null,
  mapDispatchToProps
)(US005Redux);
