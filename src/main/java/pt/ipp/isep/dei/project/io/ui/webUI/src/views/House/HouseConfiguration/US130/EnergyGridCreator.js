import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import {fetchEnergyGrid} from "./Actions";
import {connect} from 'react-redux';

class EnergyGridCreator extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      name: '...',
      maxContractedPower: 0
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };
  }

  handleSubmit(){
    this.props.onFetchEnergyGrid(this.state);
  }

  render() {
    const {name, maxContractedPower} = this.state;
    return (
      <>
        <label> Name:
          <input value={this.state.name} type="text" name="name" onChange={this.handleInputChange('name')}/>
        </label>

        <label> maxContractedPower:
          <input value={this.state.maxContractedPower} type="number" name="maxContractedPower" onChange={this.handleInputChange('maxContractedPower')}/>
        </label>

        <p>The energy grid to be created has the following details: {name + ', ' + maxContractedPower + '.'}</p>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.handleSubmit}>Save new energy grid</Button>
      </>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchEnergyGrid: ({name, maxContractedPower}) => {
      dispatch(fetchEnergyGrid({name, maxContractedPower}))
    }
  }
};

export default connect(null,mapDispatchToProps)(EnergyGridCreator);

