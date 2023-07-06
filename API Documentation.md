# Timetable System REST API

This is a simple timetable system for The School of Computing and Mathematical Sciences at [University of Leicester](https://le.ac.uk) to organise and manage modules\' timetables. Its keeps track of convenors, the modules they teach, and the sessions delivered in each module. <br /> <br /> Below is detailed documentation of all end points, parameters, error messages available in the Timetable system.

## Version: 1.0.0

### /convenors

#### GET

##### Summary:

Get all the list of available convenor

##### Responses

| Code | Description |
| ---- | ----------- |
| 200  |             |
| 404  | Not found   |

#### POST

##### Summary:

Add new convenor

##### Responses

| Code | Description                       |
| ---- | --------------------------------- |
| 201  | New convenor successfully created |
| 400  | Bad request                       |
| 409  | Convenor id already exists        |

### /convenors/{id}

#### GET

##### Summary:

Get convenor by id

##### Parameters

| Name | Located in | Description                            | Required | Schema |
| ---- | ---------- | -------------------------------------- | -------- | ------ |
| id   | path       | The id of the convenor to be retrieved | Yes      | long   |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200  |             |
| 404  | Not found   |

#### PUT

##### Summary:

Update convenor by id

##### Parameters

| Name | Located in | Description                          | Required | Schema |
| ---- | ---------- | ------------------------------------ | -------- | ------ |
| id   | path       | The id of the convenor to be updated | Yes      | long   |

##### Responses

| Code | Description                   |
| ---- | ----------------------------- |
| 200  | convenor successfully updated |
| 404  | convenor not found            |

#### DELETE

##### Summary:

Delete convenor by id

##### Parameters

| Name | Located in | Description                          | Required | Schema |
| ---- | ---------- | ------------------------------------ | -------- | ------ |
| id   | path       | The id of the convenor to be deleted | Yes      | long   |

##### Responses

| Code | Description                   |
| ---- | ----------------------------- |
| 200  | convenor successfully deleted |
| 400  | Bad request                   |
| 404  | convenor not found            |

### /convenors/{id}/modules

#### GET

##### Summary:

Get all modules of a convenor

##### Parameters

| Name | Located in | Description                            | Required | Schema |
| ---- | ---------- | -------------------------------------- | -------- | ------ |
| id   | path       | The id of the convenor to be retrieved | Yes      | long   |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200  |             |
| 404  | Not found   |

### /modules

#### GET

##### Summary:

Get all modules

##### Responses

| Code | Description |
| ---- | ----------- |
| 200  |             |
| 404  | Not found   |

#### POST

##### Summary:

Add new module

##### Responses

| Code | Description                     |
| ---- | ------------------------------- |
| 201  | New module successfully created |
| 400  | Bad request                     |
| 409  | Module id already exists        |

### /modules/{id}

#### GET

##### Summary:

Get module by id

##### Parameters

| Name | Located in | Description                          | Required | Schema |
| ---- | ---------- | ------------------------------------ | -------- | ------ |
| id   | path       | The id of the module to be retrieved | Yes      | long   |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200  |             |
| 404  | Not found   |

#### DELETE

##### Summary:

Delete module by id

##### Parameters

| Name | Located in | Description                        | Required | Schema |
| ---- | ---------- | ---------------------------------- | -------- | ------ |
| id   | path       | The id of the module to be deleted | Yes      | long   |

##### Responses

| Code | Description                 |
| ---- | --------------------------- |
| 200  | module successfully deleted |
| 404  | module not found            |

#### PATCH

##### Summary:

Update module by id

##### Parameters

| Name | Located in | Description                        | Required | Schema |
| ---- | ---------- | ---------------------------------- | -------- | ------ |
| id   | path       | The id of the module to be updated | Yes      | long   |

##### Responses

| Code | Description                 |
| ---- | --------------------------- |
| 200  | module successfully updated |
| 404  | module not found            |

### /modules/{code}/sessions

#### GET

##### Summary:

Get all sessions of a module

##### Parameters

| Name | Located in | Description                            | Required | Schema |
| ---- | ---------- | -------------------------------------- | -------- | ------ |
| code | path       | The code of the module to be retrieved | Yes      | string |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200  |             |
| 404  | Not found   |

#### POST

##### Summary:

Add new session to a module

##### Parameters

| Name | Located in | Description                            | Required | Schema |
| ---- | ---------- | -------------------------------------- | -------- | ------ |
| code | path       | The code of the module to be retrieved | Yes      | string |

##### Responses

| Code | Description                      |
| ---- | -------------------------------- |
| 201  | New session successfully created |
| 400  | Bad request                      |
| 404  | Module not found                 |
| 409  | Session id already exists        |

### /modules/{code}/sessions/{id}

#### GET

##### Summary:

Get session by id

##### Parameters

| Name | Located in | Description                            | Required | Schema |
| ---- | ---------- | -------------------------------------- | -------- | ------ |
| code | path       | The code of the module to be retrieved | Yes      | string |
| id   | path       | The id of the session to be retrieved  | Yes      | long   |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200  |             |
| 404  | Not found   |

#### DELETE

##### Summary:

Delete session by id

##### Parameters

| Name | Located in | Description                            | Required | Schema |
| ---- | ---------- | -------------------------------------- | -------- | ------ |
| code | path       | The code of the module to be retrieved | Yes      | string |
| id   | path       | The id of the session to be deleted    | Yes      | long   |

##### Responses

| Code | Description                  |
| ---- | ---------------------------- |
| 200  | session successfully deleted |
| 404  | not found                    |

#### PATCH

##### Summary:

Update session by id

##### Parameters

| Name | Located in | Description                            | Required | Schema |
| ---- | ---------- | -------------------------------------- | -------- | ------ |
| code | path       | The code of the module to be retrieved | Yes      | string |
| id   | path       | The id of the session to be updated    | Yes      | long   |

##### Responses

| Code | Description                  |
| ---- | ---------------------------- |
| 200  | session successfully updated |
| 404  | session not found            |

#### PUT

##### Summary:

Update session by id

##### Parameters

| Name | Located in | Description                            | Required | Schema |
| ---- | ---------- | -------------------------------------- | -------- | ------ |
| code | path       | The code of the module to be retrieved | Yes      | string |
| id   | path       | The id of the session to be updated    | Yes      | long   |

##### Responses

| Code | Description                  |
| ---- | ---------------------------- |
| 200  | session successfully updated |
| 404  | not found                    |

### /sessions

#### GET

##### Summary:

Get sessions

##### Parameters

| Name     | Located in | Description                                                       | Required | Schema |
| -------- | ---------- | ----------------------------------------------------------------- | -------- | ------ |
| convenor | query      | The convenor of the sessions to be retrieved by given convenor id | No       | long   |
| module   | query      | The module of the sessions to be retrieved by given module code   | No       | string |

##### Responses

| Code | Description                     |
| ---- | ------------------------------- |
| 200  | sessions successfully retrieved |
| 404  | not found                       |

#### DELETE

##### Summary:

Delete all sessions

##### Responses

| Code | Description                   |
| ---- | ----------------------------- |
| 200  | sessions successfully deleted |
| 404  | not found                     |
